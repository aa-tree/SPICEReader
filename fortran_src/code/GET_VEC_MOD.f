CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


        SUBROUTINE GET_VEC_MOD(I_VEC, O_MOD)
        IMPLICIT NONE

        INCLUDE 'common.dat'



        REAL*8 I_VEC(6)
        REAL*8 O_MOD




        O_MOD=I_VEC(1)**2+ I_VEC(2)**2+ I_VEC(3)**2
        O_MOD= SQRT(O_MOD)

        RETURN
        END
