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

        REAL*8 ET
        CHARACTER*100 TIME_C

        CALL STARTSYS(0)    

        %_DEFINE_VARIABLES%

        %_TIME_CONVERSION_F%

        WRITE(*,*) %_OUTPUT_COMMANDS%


        RETURN
        END PROGRAM
