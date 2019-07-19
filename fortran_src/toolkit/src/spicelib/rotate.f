C$Procedure      ROTATE ( Generate a rotation matrix )
 
      SUBROUTINE ROTATE ( ANGLE, IAXIS, MOUT )
 
C$ Abstract
C
C      Calculate the 3x3 rotation matrix generated by a rotation
C      of a specified angle about a specified axis. This rotation
C      is thought of as rotating the coordinate system.
C
C$ Disclaimer
C
C     THIS SOFTWARE AND ANY RELATED MATERIALS WERE CREATED BY THE
C     CALIFORNIA INSTITUTE OF TECHNOLOGY (CALTECH) UNDER A U.S.
C     GOVERNMENT CONTRACT WITH THE NATIONAL AERONAUTICS AND SPACE
C     ADMINISTRATION (NASA). THE SOFTWARE IS TECHNOLOGY AND SOFTWARE
C     PUBLICLY AVAILABLE UNDER U.S. EXPORT LAWS AND IS PROVIDED "AS-IS"
C     TO THE RECIPIENT WITHOUT WARRANTY OF ANY KIND, INCLUDING ANY
C     WARRANTIES OF PERFORMANCE OR MERCHANTABILITY OR FITNESS FOR A
C     PARTICULAR USE OR PURPOSE (AS SET FORTH IN UNITED STATES UCC
C     SECTIONS 2312-2313) OR FOR ANY PURPOSE WHATSOEVER, FOR THE
C     SOFTWARE AND RELATED MATERIALS, HOWEVER USED.
C
C     IN NO EVENT SHALL CALTECH, ITS JET PROPULSION LABORATORY, OR NASA
C     BE LIABLE FOR ANY DAMAGES AND/OR COSTS, INCLUDING, BUT NOT
C     LIMITED TO, INCIDENTAL OR CONSEQUENTIAL DAMAGES OF ANY KIND,
C     INCLUDING ECONOMIC DAMAGE OR INJURY TO PROPERTY AND LOST PROFITS,
C     REGARDLESS OF WHETHER CALTECH, JPL, OR NASA BE ADVISED, HAVE
C     REASON TO KNOW, OR, IN FACT, SHALL KNOW OF THE POSSIBILITY.
C
C     RECIPIENT BEARS ALL RISK RELATING TO QUALITY AND PERFORMANCE OF
C     THE SOFTWARE AND ANY RELATED MATERIALS, AND AGREES TO INDEMNIFY
C     CALTECH AND NASA FOR ALL THIRD-PARTY CLAIMS RESULTING FROM THE
C     ACTIONS OF RECIPIENT IN THE USE OF THE SOFTWARE.
C
C$ Required_Reading
C
C     None.
C
C$ Keywords
C
C      MATRIX,  ROTATION
C
C$ Declarations
 
      DOUBLE PRECISION   ANGLE
      INTEGER            IAXIS
      DOUBLE PRECISION   MOUT  ( 3,3 )
 
C$ Brief_I/O
C
C      VARIABLE  I/O              DESCRIPTION
C      --------  ---  --------------------------------------------------
C       ANGLE     I     Angle of rotation (radians).
C       IAXIS     I     Axis of rotation (X=1, Y=2, Z=3).
C       MOUT      O     Resulting rotation matrix [ANGLE]
C                                                       IAXIS
C$ Detailed_Input
C
C      ANGLE   The angle given in radians, through which the rotation
C              is performed.
C
C      IAXIS   The index of the axis of rotation.  The X, Y, and Z
C              axes have indices 1, 2 and 3 respectively.
C
C$ Detailed_Output
C
C      MOUT    Rotation matrix which describes the rotation of the
C               COORDINATE system through ANGLE radians about the
C               axis whose index is IAXIS.
C
C$ Parameters
C
C      None.
C
C$ Particulars
C
C      A rotation about the first, i.e. x-axis, is described by
C
C      |  1        0          0      |
C      |  0   cos(theta) sin(theta)  |
C      |  0  -sin(theta) cos(theta)  |
C
C      A rotation about the second, i.e. y-axis, is described by
C
C      |  cos(theta)  0  -sin(theta)  |
C      |      0       1        0      |
C      |  sin(theta)  0   cos(theta)  |
C
C      A rotation about the third, i.e. z-axis, is described by
C
C      |  cos(theta) sin(theta)   0   |
C      | -sin(theta) cos(theta)   0   |
C      |       0          0       1   |
C
C      ROTATE decides which form is appropriate according to the value
C      of IAXIS.
C
C$ Examples
C
C      If ROTATE is called from a FORTRAN program as follows:
C
C            CALL ROTATE (PI/4, 3, MOUT)
C
C      then MOUT will be given by
C
C                   | SQRT(2)/2   SQRT(2)/2   0  |
C            MOUT = |-SQRT(2)/2   SQRT(2)/2   0  |
C                   |     0           0       1  |
C
C$ Restrictions
C
C      None.
C
C$ Exceptions
C
C     Error free.
C
C     1) If the axis index is not in the range 1 to 3 it will be
C        treated the same as that integer 1, 2, or 3 that is congruent
C        to it mod 3.
C
C$ Files
C
C      None.
C
C$ Author_and_Institution
C
C      W.M. Owen       (JPL)
C      W.L. Taber      (JPL)
C
C$ Literature_References
C
C      None.
C
C$ Version
C
C-    SPICELIB Version 1.0.1, 10-MAR-1992 (WLT)
C
C        Comment section for permuted index source lines was added
C        following the header.
C
C-    SPICELIB Version 1.0.0, 31-JAN-1990 (WMO)
C
C-&
 
C$ Index_Entries
C
C     generate a rotation matrix
C
C-&
 
 
C$ Revisions
C
C-    Beta Version 1.1.0, 3-JAN-1989 (WLT)
C
C     Upgrade the routine to work with negative axis indexes.  Also take
C     care of the funky way the indices (other than the input) were
C     obtained via the MOD function.  It works but isn't as clear
C     (or fast) as just reading the axes from data.
C
C-&
C
C
      DOUBLE PRECISION      S
      DOUBLE PRECISION      C
 
      INTEGER               TEMP
      INTEGER               I1
      INTEGER               I2
      INTEGER               I3
      INTEGER               INDEXS ( 5 )
      SAVE                  INDEXS
 
      DATA                  INDEXS  / 3, 1, 2, 3, 1 /
C
C  Get the sine and cosine of ANGLE
C
      S = DSIN(ANGLE)
      C = DCOS(ANGLE)
C
C  Get indices for axes. The first index is for the axis of rotation.
C  The next two axes follow in right hand order (XYZ).  First get the
C  non-negative value of IAXIS mod 3 .
C
      TEMP = MOD ( MOD(IAXIS,3) + 3, 3 )
 
      I1 = INDEXS( TEMP + 1 )
      I2 = INDEXS( TEMP + 2 )
      I3 = INDEXS( TEMP + 3 )
 
C
C  Construct the rotation matrix
C
      MOUT(I1,I1) = 1.D0
      MOUT(I2,I1) = 0.D0
      MOUT(I3,I1) = 0.D0
      MOUT(I1,I2) = 0.D0
      MOUT(I2,I2) = C
      MOUT(I3,I2) = -S
      MOUT(I1,I3) = 0.D0
      MOUT(I2,I3) = S
      MOUT(I3,I3) = C
C
      RETURN
      END
 
