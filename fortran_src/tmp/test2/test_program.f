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

        REAL*8 FACT, K_POW, X_VAL
        REAL*8 STATE(6), OSTATE(6)


        K_POW=127.1452D0
        X_VAL=0.02D0
        CALL GETBINOMIAL_SUM(K_POW, X_VAL, FACT)

        WRITE(*,*) FACT



        RETURN
        END PROGRAM
