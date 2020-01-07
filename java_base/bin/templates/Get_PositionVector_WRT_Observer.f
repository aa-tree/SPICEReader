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

        CALL GET_POS_WRT_IBC(ET, %_TARGET_VALUE%, O_POS)

        DO I=1,6
            WRITE(*,*) O_POS(I)
        END DO

        RETURN
        END PROGRAM
