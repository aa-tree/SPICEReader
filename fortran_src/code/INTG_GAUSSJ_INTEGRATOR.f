CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


        SUBROUTINE GAUSSJ_INTEGRATOR(VTIME, VSTATE, VDIM, DELTAH,
     & V_TFINAL, STARTUP_ROUTINE, VECTOR_ROUTINE, OSTATE)

        IMPLICIT NONE
        INCLUDE 'common.dat'

        INTEGER VDIM
        REAL*8 VTIME, V_TFINAL, DELTAH
        REAL*8 VSTATE(VDIM), OSTATE(VDIM)

        EXTERNAL STARTUP_ROUTINE, VECTOR_ROUTINE


C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

        REAL*8  ERROR_VAL(2)
        REAL*8 RK_T_VS(VDIM), RK_T_DELTAH !Temp variables for startup.
        REAL*8 RK_VTIME  !Temp variables for startup.

        REAL*8 R_M4_4(VDIM, 9), R_M4_4_TEMP(VDIM)
        REAL*8 R_M4_4_P(VDIM, 9), R_M4_4_P_TEMP(VDIM)

        INTEGER I,J,K



        REAL*8 TEMP_T, TEMP_DET
        REAL*8 O_R_N_P(3,9), O_R_N(3,9)

        REAL*8 T_VEXACT_CONV(3), T_VAPPROX_COV(3), T_ERR_CONV(2)

C
C       Override VDIM's value.
C

        VDIM=6

C
C       Initialise some integrator/function constants variables
C


        ERROR_VAL(1)=1.0D-03 !Absolute error limit.
        ERROR_VAL(2)=1.0D-05 !Relative error limit.

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!       STARTUP STEPS
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

C
C       Initilse r and r' vector with zeroes.
C

        DO J=1,9
            DO I=1,VDIM
                R_M4_4(I,J)=0.0D0
            END DO
        END DO

C
C       Get the eight required startup values r4, r3...r1, and r-4..r-1
C       The vector R_M4_4 has a bias of 5. So r-3 would be in the 2 col.
C
        DO J=1,VDIM !Move r_0 into the common vector.

            R_M4_4(J,5)=VSTATE(J)

        END DO

        RK_VTIME=VTIME
        RK_T_DELTAH= -1.0D0*DELTAH


        DO I=4,1,-1 !Calc r_-1 to r_-4

            DO J=1,VDIM
                RK_T_VS(J)=R_M4_4(J,I+1)
            END DO


            CALL STARTUP_ROUTINE(RK_VTIME, VDIM, RK_T_VS, RK_T_DELTAH,
     & VECTOR_ROUTINE, R_M4_4_TEMP)

            DO J=1,VDIM
                R_M4_4(J,I)=R_M4_4_TEMP(J)
            END DO

            RK_VTIME=RK_VTIME-DELTAH

        END DO



        RK_VTIME=VTIME

        DO I=6,9 !r1 to r4

            DO J=1,VDIM
                RK_T_VS(J)=R_M4_4(J,I-1)
            END DO

            CALL STARTUP_ROUTINE(VTIME, VDIM, RK_T_VS, DELTAH,
     & VECTOR_ROUTINE, R_M4_4_TEMP)

            DO J=1,VDIM
                R_M4_4(J, I)=R_M4_4_TEMP(J)
            END DO

            RK_VTIME=RK_VTIME+DELTAH
        END DO

        DO J=1,9

            DO I=1,6
                WRITE(*,*) R_M4_4(I,J), '(', J , I, ')'
            END DO
            WRITE(*,*) 'MOD', (R_M4_4(1,J)**2+R_M4_4(2,J)**2
     & +R_M4_4(3,J)**2)**0.50D0
            WRITE(*,*) '================='

        END DO

        PAUSE



C
C       Get the accelerations at r-4, r-3....r4
C

        TEMP_T=VTIME-(4.0D0*DELTAH)

        DO I=1,9

            DO J=1,VDIM
                R_M4_4_TEMP(J)=R_M4_4(J,I)
            END DO

            CALL VECTOR_ROUTINE(TEMP_T, R_M4_4_TEMP, VDIM,
     &R_M4_4_P_TEMP)

            DO J=1,VDIM
                R_M4_4_P(J,I)=R_M4_4_P_TEMP(J)
            END DO

            TEMP_T=TEMP_T+DELTAH

        END DO





C
C       Test convergence of accelerations.
C

10      DO I=1,3 !Save r_0''.
            T_VEXACT_CONV(I)=R_M4_4_P(I+3,5)
        END DO


        DO J=1,9

            IF(J .NE. 5) THEN

            DO I=1,3
            T_VAPPROX_COV(I)=R_M4_4_P(I+3,J)
            END DO

            CALL CONVERGENCE_TEST_VECTOR(T_VAPPROX_COV, T_VEXACT_CONV,
     & T_ERR_CONV)

            WRITE(*,*) T_ERR_CONV(1), T_ERR_CONV(2)

            PAUSE

!            IF(T_ERR_CONV(1) .GT. ERROR_VAL(1)) WRITE(*,*) T_ERR_CONV(1)

!            IF(T_ERR_CONV(2) .GT. ERROR_VAL(2)) WRITE(*,*) T_ERR_CONV(2)


            END IF

        END DO
C
C       Calc r_n & r_n', for n=-4..4, n != 0
C


20       CALL GAUSS_CALC_RN (VDIM, R_M4_4, R_M4_4_P,
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

        GOTO 10 !Test Convergence Again.

C
C       Now that the accelerations have converged. We move on to
C       the predictor.
C





        RETURN
        END

