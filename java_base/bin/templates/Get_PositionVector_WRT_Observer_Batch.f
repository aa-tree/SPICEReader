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

        CALL STARTSYS(%_IBC_VALUE%)

        %_TIME_CONVERSION_F%

        %_POSITION_COMMANDS_BATCH%

        RETURN
        END PROGRAM
