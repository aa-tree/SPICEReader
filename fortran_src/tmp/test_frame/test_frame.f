CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC



        PROGRAM TEST2
        IMPLICIT NONE

        INCLUDE '../../code/common.dat'

        REAL*8 STATE(6), TSTATE(6)
        REAL*8 IT, ET, LET, DIST_T

        INTEGER I


        CALL STARTSYS(3)
        CALL START_TWOBODYSYSTEM(399)

        CALL STR2ET ( '2019 JAN 31 01:00', ET )

        CALL SPKEZ ( 301, ET, 'J2000', 'NONE',0, STATE, LET )
        CALL TRANS_ORIGIN_FROMSSB(ET, 0, STATE, TSTATE)

        CALL GET_VEC_MOD(TSTATE, DIST_T)

        DO I=1,6
          TSTATE(I)=TSTATE(I)-STATE(I)
        END DO


        DO I=1,6
          WRITE(*,*) TSTATE(I)
        END DO

        WRITE(*,*) DIST_T









        RETURN
        END PROGRAM
