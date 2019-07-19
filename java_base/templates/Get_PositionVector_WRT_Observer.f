CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Created by: SPICE_UI
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

        
        PROGRAM %_PROGRAMNAME%
        IMPLICIT NONE

        INCLUDE '../../code/common.dat'

        REAL*8  ET, O_POS(6)
        INTEGER I_ID, I

        EXTERNAL  GET_POS_WRT_IBC

        CALL STARTSYS(%_IBC_VALUE%)
        CALL GET_POS_WRT_IBC(ET, I_ID, O_POS)

        WRITE(*,*) O_POS(I)

        RETURN
        END PROGRAM
