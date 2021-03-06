CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


        SUBROUTINE GAUSS_CALC_CAP_SN(VDIM, V_R_M4_4, V_R_M4_4_P,
     &VDELTAH, V_S_N, O_CAP_S_N)

        IMPLICIT NONE
        INCLUDE 'common.dat'

        INTEGER VDIM
        REAL*8 V_R_M4_4(VDIM, 9), V_R_M4_4_P(VDIM, 9)
        REAL*8 VDELTAH
        REAL*8 V_S_N(3,9), O_CAP_S_N(3,9)

C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

        REAL*8 C_1_P, C_1, C_2
        REAL*8 TEMP_CAP_S_0
        REAL*8 TEMP_V(VDIM), TEMP_MOD
        REAL*8 G_ajk(10, 9)
        INTEGER I,J


        CALL GAUSS_GET_COEFF('a', G_ajk)

C
C       Set all the output to 0.
C
        DO J=1,9
            DO I=1,3
                O_CAP_S_N(I,J)=0.0D0
            END DO
        END DO

C
C       For n=0.
C
        DO J=1,9
            DO I=1,3
                O_CAP_S_N(I,5)=V_R_M4_4(I,5)/(VDELTAH)**2.0D0
                O_CAP_S_N(I,5)=O_CAP_S_N(I,5)-(G_ajk(5,J)*
     & V_R_M4_4_P(I,J))
            END DO
        END DO

C
C       For n>0.
C


        DO J=6,9
            DO I=1,3
                O_CAP_S_N(I,J)=V_R_M4_4_P(I+3,J-1)/2.0D0
                O_CAP_S_N(I,J)=O_CAP_S_N(I,J)+ O_CAP_S_N(I,J-1)
     & +V_S_N(I,J-1)
            END DO
        END DO

C
C       For n<0.
C

        DO J=1,4
            DO I=1,3
                O_CAP_S_N(I,J)=V_R_M4_4_P(I+3,J+1)/2.0D0
                O_CAP_S_N(I,J)=O_CAP_S_N(I,J)+O_CAP_S_N(I,J+1)
     & -V_S_N(I,J+1)
            END DO
        END DO

        RETURN
        END SUBROUTINE
