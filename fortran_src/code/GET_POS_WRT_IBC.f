CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC



      SUBROUTINE GET_POS_WRT_IBC(ET, I_ID, O_POS)


      IMPLICIT NONE

      INCLUDE 'common.dat'

      REAL*8 ET
      INTEGER I_ID
      REAL*8 O_POS(6)

!
!       SUMMARY:
!       This routine gets the position vector of a body w.r.t. to
!       the SSB(Solar system Barycenter [ID=0]), at a particular time.
!
!       VARIABLES:
!
!       NAME         TYPE                  DESCRIPTION
!
!       ET           INPUT(REAL*8)         The time at which the
!                                          state vector is needed.
!
!       I_ID         INPUT(INTEGER)        The ID of the target
!                                          body.

!       O_POS(6)     OUTPUT(REAL*8)        The position vector.
!
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

      REAL*8 RJ0(6), LTIME
      INTEGER I



      CALL SPKEZ(I_ID, ET, 'J2000', 'NONE', IBC, RJ0, LTIME)



      DO I=1,6
        O_POS(I)=RJ0(I)
      END DO


      !CALL TRANS_ORIGIN_FROMSSB(ET, IBC, RJ0, O_POS)


      RETURN
      END
