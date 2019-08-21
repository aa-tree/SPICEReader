CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC



      SUBROUTINE GET_POS_WRT_BODYID(ET, I_ID, I_BODY, O_POS)





      IMPLICIT NONE

      INCLUDE 'common.dat'

      REAL*8 ET
      INTEGER I_ID, I_BODY
      REAL*8 O_POS(6)

CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

      REAL*8 RJ0(6), LTIME
      INTEGER I



      CALL SPKEZ(I_ID, ET, 'J2000', 'NONE', I_BODY, RJ0, LTIME)



      DO I=1,6
        O_POS(I)=RJ0(I)
      END DO


      !CALL TRANS_ORIGIN_FROMSSB(ET, IBC, RJ0, O_POS)


      RETURN
      END