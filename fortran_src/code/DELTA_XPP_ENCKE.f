CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C       Written by: Anshuk Attri
C
C       Contact: contact@anshukattri.in
C       Website: www.anshukattri.in/research
C       GITHUB: github.com/aa-tree/
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


        SUBROUTINE DELTA_XPP_ENCKE(VTIME, V_DELR, VSTATE, VRHO,
     &VDIM, OSTATE)

        IMPLICIT NONE

        INCLUDE 'common.dat'

        INTEGER VDIM
        REAL*8 VTIME, V_DELR(VDIM)
        REAL*8 VSTATE(VDIM), OSTATE(VDIM), VRHO(VDIM)


        INTEGER  I, J

        REAL*8 MOD_R, MOD_RHO, Q_VAR
        REAL*8 TEMP1, SUM_B, TEMP_MOD
        REAL*8 MU_P, MU_J

        REAL*8 DELTAR(VDIM)
        REAL*8 PERT_GRAV(3,MMAX)

        REAL*8 RJ(VDIM), RJI(VDIM)

!
!       Assign zereos to the output vector.
!

        DO I=1,VDIM
          OSTATE(I)=0.00D0
        END DO

        DO I=1,3
          OSTATE(I)=VSTATE(I+3)-VRHO(I+3)
        END DO

C
C       Calculate mods of vectors r and rho. And then calculate q.
C

        CALL GET_VEC_MOD(VSTATE, MOD_R)
        CALL GET_VEC_MOD(VRHO, MOD_RHO)
C        WRITE(*,*) 'MOD_R', MOD_R
C        WRITE(*,*) 'MOD_RHO', MOD_RHO
C        PAUSE


        IF(MOD_RHO .NE. 0) THEN



            TEMP1=(MOD_R/MOD_RHO)**2


            Q_VAR=(1.0D0-TEMP1) ! (Holds the value of 2q)

!            WRITE(*,*) 'QVAR', Q_VAR
!            PAUSE


            TEMP1=-1.50D0

            CALL GETBINOMIAL_SUM(TEMP1, Q_VAR, SUM_B)

            CALL GET_MU(IPB, MU_P)


            DO I=1,6
              DELTAR(I)=VSTATE(I)-VRHO(I)
            END DO

C
C       The primary body term of the Encke's formulation.
C


            DO I=1,3
              TEMP1=((1.0D0-SUM_B)*VSTATE(I))-DELTAR(I)
              OSTATE(I+3)=(MU_P*TEMP1)/(MOD_RHO**3.0D0)
              !PERT_GRAV(I,PRB_ID)=OSTATE(I) !Assign for analysis.
            END DO

!
!       To get the term a_p
!

!
!       Gravitational Perturbations from the model.
!

            DO J=1,MMAX

            IF(LIST_I(J,1) .NE. IPB) THEN

              CALL GET_POS_WRT_IBC(VTIME, LIST_I(J,1), RJ)

              DO I=1,6
                  RJI(I)= VSTATE(I)-RJ(I)
              END DO

              CALL GET_VEC_MOD(RJI, TEMP_MOD)

              IF(TEMP_MOD .NE. 0 ) THEN

                      MU_J=0.D0
                      CALL GET_MU(LIST_I(J,1), MU_J)

                  DO I=1,3
                      TEMP1=(-1.0D0*MU_J)*RJI(I)
                      TEMP1=TEMP1/(TEMP_MOD**3.0D0)
                    !  PERT_GRAV(I,J)=TEMP1 ! Saving Perturbations for analysis.
                      OSTATE(I+3)=OSTATE(I+3)+TEMP1
                  END DO

              END IF

          END IF
          END DO

        ELSE
            WRITE(*,*) 'Error in DELTA_XPP_ENCKE. Mod of the vector rho'
            WRITE(*,*) ' is zero.'

        END IF


        RETURN
        END
