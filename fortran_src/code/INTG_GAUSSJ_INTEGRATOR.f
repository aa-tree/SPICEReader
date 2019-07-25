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


        REAL*8 DELTAH
        REAL*8 R_M4_4(VDIM, 9), R_M4_4_TEMP(VDIM)
        REAL*8 R_M4_4_P(VDIM, 9), R_M4_4_P_TEMP(VDIM)

        INTEGER I,J

        REAL*8 TEMP_T, TEMP_DET
        REAL*8 O_R_N_P(3,9), O_R_N(3,9)


C
C       Initialise some integrator variables
C

        DELTAH = 1.0D-06

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!       STARTUP STEPS
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



C
C       Get the eight required startup values r4, r3...r1, and r-4..r-1
C       The vector R_M4_4 has a bias of 5. So r-3 would be in the 2 col.
C

        TEMP_T=VTIME-(4.0D0*DELTAH)

        DO I=1,4 !r-4 to r-1

            CALL STARTUP_ROUTINE(TEMP_T, VDIM, VSTATE, VTIME,
     & VECTOR_ROUTINE, R_M4_4_TEMP)

            DO J=1,VDIM
                R_M4_4(J, I)=R_M4_4_TEMP(J)
            END DO

            TEMP_T=TEMP_T+DELTAH

        END DO


        DO J=1,6 !Move r_0 into the common vector.

            R_M4_4(J,5)=VSTATE(J)

        END DO


        TEMP_T=VTIME+DELTAH

        DO I=6,9 !r1 to r4

            CALL STARTUP_ROUTINE(TEMP_T, VDIM, VSTATE, VTIME,
     & VECTOR_ROUTINE, R_M4_4_TEMP)

            DO J=1,VDIM
                R_M4_4(J, I)=R_M4_4_TEMP(J)
            END DO

            TEMP_T=TEMP_T+DELTAH

        END DO


C
C       Get the accelerations at r-4, r-3....r4
C

        TEMP_T=VTIME-(4.0D0*DELTAH)

        DO I=1,9

            DO J=1,6
                R_M4_4_TEMP(J)=R_M4_4(J,I)
            END DO

            CALL VECTOR_ROUTINE(TEMP_T, R_M4_4_TEMP, VDIM,
     &R_M4_4_P_TEMP)

            DO J=1,6
                R_M4_4_P(J,I)=R_M4_4_P_TEMP(J)
            END DO

            TEMP_T=TEMP_T+DELTAH

        END DO

C
C       Test convergence of accelerations.
C

C
C       Calc r_n & r_n', for n=-4..4, n != 0
C


        CALL GAUSS_CALC_RN (VDIM, R_M4_4, R_M4_4_P,
     & DELTAH, O_R_N_P, O_R_N)

        DO I=1,3
            DO J=1,9
                R_M4_4(I,J)=O_R_N(I,J)
            END DO
        END DO

        DO I=4,6
            DO J=1,9
                R_M4_4_P(I,J)=O_R_N_P(I,J)
            END DO
        END DO






        RETURN
        END

