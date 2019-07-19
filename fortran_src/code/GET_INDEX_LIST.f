CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


        SUBROUTINE GET_INDEX_LIST(ID, R_INDEX)
        IMPLICIT NONE

        INCLUDE 'common.dat'


        INTEGER ID, R_INDEX, I


C
C       V_ID        INPUT [INTEGER]      ID of the body to search.
C
C       R_INDEX     OUTPUT [INTEGER]     The index of the requested body in 
C                                        the common variable LIST_I.
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


        R_INDEX=0

        DO I=1,MMAX
            IF(LIST_I(I,1) .EQ. ID) THEN
                R_INDEX=I
            END IF
        END DO



        RETURN
        END SUBROUTINE
