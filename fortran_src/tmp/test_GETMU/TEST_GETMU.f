CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC



        PROGRAM TEST_GETMU
        IMPLICIT NONE

        INCLUDE '../../code/common.dat'

        INTEGER INPUT_ID
        REAL*8 MU_TEMP

        CALL STARTSYS

        READ(*,*) INPUT_ID

        CALL GET_MU (INPUT_ID, MU_TEMP)

        WRITE(*,*) MU_TEMP



        RETURN
        END PROGRAM
