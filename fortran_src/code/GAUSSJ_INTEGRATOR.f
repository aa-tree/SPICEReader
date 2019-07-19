CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


        SUBROUTINE GAUSSJ_INTEGRATOR(VTIME, VSTATE, VDIM, VDET, VFINAL,
     & STARTUP_ROUTINE, VECTOR_ROUTINE, OSTATE)

        IMPLICIT NONE
        INCLUDE 'common.dat'

        INTEGER VDIM
        REAL*8 VTIME, VDET, VFINAL
        REAL*8 VSTATE (VDIM), OSTATE(VDIM)

        EXTERNAL STARTUP_ROUTINE, VECTOR_ROUTINE


        REAL*8 G_bjk(10, 9). G_ajk(10, 9)

        REAL*8 DELTAH
        REAL*8 R4(VDIM),R3(VDIM), R2(VDIM), R1(VDIM)
        REAL*8 R_M4(VDIM),R_M3(VDIM), R_M2(VDIM), R_M1(VDIM)
        REAL*8 R4_PP(VDIM),R3_PP(VDIM), R2_PP(VDIM), R1_PP(VDIM)
        REAL*8 R_M4_PP(VDIM),R_M3_PP(VDIM), R_M2_PP(VDIM), R_M1_PP(VDIM)


        REAL*8 s_0(3), s_n(3,4), s_Mn(3,4)
        INTEGER I,J
        REAL*8 TEMP_T, TEMP_DET


C
C       Initialise some integrator variables
C

        DELTAH = 1.0D-06

C
C       Get Gauss Jackson coefficients.
C

        CALL GET_GAUSS_COEFF('b', G_bjk)
        CALL GET_GAUSS_COEFF('a', G_ajk)


C
C       Get the eight required startup values r4, r3...r1, and r-4..r-1
C


        CALL STARTUP_ROUTINE( VTIME, VDIM, VSTATE, DET, VECTOR_ROUTINE
     &, R1)


        TEMP_T=VTIME+DELTAH

        CALL STARTUP_ROUTINE( TEMP_T, VDIM, R1, DET, VECTOR_ROUTINE
     &, R2)

        TEMP_T=VTIME+2.0D0*DELTAH

        CALL STARTUP_ROUTINE( TEMP_T, VDIM, R2, DET, VECTOR_ROUTINE
     &, R3 )

        TEMP_T=VTIME+3.0D0*DELTAH

        CALL STARTUP_ROUTINE( TEMP_T, VDIM, R3, DET, VECTOR_ROUTINE
     &, R4)



        TEMP_DET=-1.0D0*DET

        CALL STARTUP_ROUTINE( VTIME, VDIM, VSTATE, TEMP_DET,
     & VECTOR_ROUTINE, R_M1 )

        TEMP_T=VTIME-DELTAH

        CALL STARTUP_ROUTINE( TEMP_T, VDIM, R_M1, TEMP_DET,
     & VECTOR_ROUTINE, R_M2 )


        TEMP_T=VTIME-2.0D0*DELTAH

        CALL STARTUP_ROUTINE( TEMP_T, VDIM, R_M2, TEMP_DET,
     & VECTOR_ROUTINE, R_M3)

        TEMP_T=VTIME-3.0D0*DELTAH

        CALL STARTUP_ROUTINE( TEMP_T, VDIM, R_M3, TEMP_DET,
     & VECTOR_ROUTINE, R_M4 )


C
C       Get the accelerations at r-4, r-3....r4
C

        TEMP_T=VTIME+DELTAH

        CALL VECTOR_ROUTINE(TEMP_T, R1, VDIM, R1_PP)

        TEMP_T=VTIME+2.0D0*DELTAH

        CALL VECTOR_ROUTINE(TEMP_T, R2, VDIM, R2_PP)

        TEMP_T=VTIME+3.0D0*DELTAH

        CALL VECTOR_ROUTINE(TEMP_T, R3, VDIM, R3_PP)

        TEMP_T=VTIME+4.0D0*DELTAH

        CALL VECTOR_ROUTINE(TEMP_T, R4, VDIM, R4_PP)


        TEMP_T=VTIME-DELTAH

        CALL VECTOR_ROUTINE(TEMP_T, R_M1, VDIM, R_M1_PP)

        TEMP_T=VTIME-2.0D0*DELTAH

        CALL VECTOR_ROUTINE(TEMP_T, R_M2, VDIM, R_M2_PP)

        TEMP_T=VTIME-3.0D0*DELTAH

        CALL VECTOR_ROUTINE(TEMP_T, R_M3, VDIM, R_M3_PP)

        TEMP_T=VTIME-4.0D0*DELTAH

        CALL VECTOR_ROUTINE(TEMP_T, R_M4, VDIM, R_M4_PP)


C
C       Test convergence of accelerations.
C

C
C       Calc s_n
C
        !n=0

        DO J=1,3

        DO I=2,4

            DO J=1,3
                s_n(J)=s



        RETURN
        END
