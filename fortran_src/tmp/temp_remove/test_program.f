CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC



      PROGRAM TEST1
      IMPLICIT NONE

      INCLUDE '../../code/common.dat'

      REAL*8 STATE(6), OSTATE(6)
      REAL*8 TSTATE(6)

      REAL*8 IT, ET, FT, DET, LET, DIST, TEMP_MOD
      REAL*8 DIST_T

      INTEGER I, FREE_FP1, COUNTV

      CHARACTER*100 FILE, FILTYP, SOURCE
      INTEGER HANDLE, DIM_V
      LOGICAL FOUND


        !FOR RK78
        REAL*8 HMIN, HMAX, TOLRK
        REAL*8 R, B, F
        DIMENSION R(13,48), B(48), F(48)
        EXTERNAL XPP_NBODY_PERT


      CALL STARTSYS(0)
      CALL START_TWOBODYSYSTEM(399)

        CALL KTOTAL ( 'SPK', COUNTV )

        IF ( COUNTV .EQ. 0 ) THEN
            WRITE (*,*) 'There are no SPK files loaded at this time.'
         ELSE
            WRITE (*,*) 'The loaded SPK files are: '
            WRITE (*,*)
         END IF

         DO I = 1, COUNTV

            CALL KDATA( I,  'SPK',  FILE, FILTYP,HANDLE, SOURCE, FOUND)
            WRITE (*,*) FILE

         END DO




      CALL STR2ET ( '2019 JAN 31 01:00', ET )

      CALL SPKEZ ( 301, ET, 'J2000', 'NONE',0, STATE, LET )

c        STATE(1)=-84797.617940104261
c        STATE(2)=-364519.38660575275
c        STATE(3)=-132918.82058874035

!
        DO I=1,6
            WRITE(*,*) STATE(I)
        END DO

        CALL GET_VEC_MOD(STATE, DIST_T)


        !STATE(4)=0.0D0
        !STATE(5)=0.0D0
        !STATE(6)=0.0D0
        WRITE(*,*) 'ET', ET
        WRITE(*,*) 'DIST', DIST_T
        WRITE(*,*) 'IBC', IBC


        WRITE(*,*) '======================'

        IT=ET
C100    FORMAT (1X,7E24.16)
        HMIN=100.00
        HMAX=1000.D0
        TOLRK=1.D-5
        DIM_V=6
        DET=864
        FT= ET+(365*86400.00D0)

        CALL GETLUN(FREE_FP1)

        OPEN(FREE_FP1, FILE='out.dat')

        DO WHILE(ET .LT. FT)

C        CALL SPKEZ ( 301, ET, 'J2000', 'NONE', 3, STATE, LET )

        CALL INTG_COMMON ('RK78',ET,DIM_V,STATE, DET, XPP_NBODY_PERT
     &,OSTATE)

c        CALL RK78 (ET,STATE,6,DET,HMIN,HMAX,TOLRK,R,B,F,XPP_TWOBODY_NO
c     &_PERT)


         ET=ET+DET
c        OSTATE=STATE

!        CALL TRANS_ORIGIN_FROMSSB(ET, 399, OSTATE, TSTATE)
        CALL GET_VEC_MOD(OSTATE, DIST_T)


        WRITE(FREE_FP1,*) ET, (OSTATE(I),I=1,6), DIST_T
        STATE=OSTATE



        !IF(DIST_T .GT. 600000.00D0 .OR. DIST_T .LT. 250000.00D0) THEN

        !WRITE(*,*) (ET-IT)/86400.0D0, DIST_T
        !PAUSE
        !END IF



        END DO

        CLOSE(FREE_FP1)


C        DO I=1,6
C             WRITE(*,*) OSTATE(I)
C         END DO


      RETURN
      END PROGRAM
