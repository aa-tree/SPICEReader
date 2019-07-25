CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


        SUBROUTINE GAUSS_CALC_RN (VDIM, V_R_M4_4, V_R_M4_4_P,
     &VDELTAH, O_R_N_P, O_R_N)

        IMPLICIT NONE
        INCLUDE 'common.dat'

        INTEGER VDIM
        REAL*8 V_R_M4_4(VDIM, 9), V_R_M4_4_P(VDIM, 9)
        REAL*8 O_R_N_P(3,9),O_R_N(3,9)
        REAL*8 VDELTAH

C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


        INTEGER I,J,K
        REAL*8 S_N(3,9), CAP_S_N(3,9)
        REAL*8 G_bjk(10, 9), G_ajk(10,9)

        CALL GAUSS_CALC_SN(VDIM, V_R_M4_4_P, VDELTAH, S_N)

        CALL GAUSS_GET_COEFF('b', G_bjk)


C
C       Set output to 0.
C

        DO J=1,9
            DO I=1,3
                O_R_N_P(I,J)=0.0D0
                O_R_N(I,J)=0.0D0
            END DO
        END DO

C
C       Calc r_n'
C

        ! r_0' is the same as the input. It is not calculated.

        DO I=1,3
            O_R_N_P(I,5)=V_R_M4_4_P(I,5)
        END DO

        DO J=1,9
        IF(J .NE. 5) THEN
            DO K=1,9
                DO I=1,3
                    O_R_N_P(I,J)=O_R_N_P(I,J)+
     & G_bjk(J,K)*V_R_M4_4_P(I+3,K)
                END DO
            END DO

            DO I=1,3
                O_R_N_P(I,J)=O_R_N_P(I,J)+S_N(I,J)
                O_R_N_P(I,J)=O_R_N_P(I,J)*VDELTAH
            END DO

        END IF
        END DO

        DO J=1,9
        IF(J .NE. 5) THEN
                DO I=1,3
                END DO
        END IF
        END DO

C
C       Calculate r_n.
C

        ! r_0' is the same as the input. It is not calculated.

        DO I=1,3
            O_R_N(I,5)=V_R_M4_4(I,5)
        END DO

        CALL GAUSS_CALC_CAP_SN(VDIM, V_R_M4_4, V_R_M4_4_P,
     &VDELTAH, S_N, CAP_S_N)

        CALL GAUSS_GET_COEFF('a', G_ajk)

        DO J=1,9
        IF (J .NE. 5) THEN
            DO K=1,9
                DO I=1,3
                    O_R_N(I,J)= O_R_N(I,J)+V_R_M4_4_P(I,K)*G_ajk(J,K)
                END DO
            END DO

            DO I=1,3
                O_R_N(I,J)= O_R_N(I,J)+CAP_S_N(I,J)
                O_R_N(I,J)=O_R_N(I,J)*(VDELTAH**2.0D0)
            END DO
        END IF
        END DO


       RETURN
       END SUBROUTINE
