C$Procedure      VSUBG ( Vector subtraction, general dimension )
 
      SUBROUTINE VSUBG ( V1, V2, NDIM, VOUT )
 
C$ Abstract
C
C     Compute the difference between two double precision vectors of
C     arbitrary dimension.
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
C     VECTOR
C
C$ Declarations
 
      INTEGER            NDIM
      DOUBLE PRECISION   V1   ( NDIM )
      DOUBLE PRECISION   V2   ( NDIM )
      DOUBLE PRECISION   VOUT ( NDIM )
 
C$ Brief_I/O
C
C     VARIABLE  I/O  DESCRIPTION
C     --------  ---  --------------------------------------------------
C     V1         I   First vector (minuend).
C     V2         I   Second vector (subtrahend).
C     NDIM       I   Dimension of V1, V2, and VOUT.
C     VOUT       O   Difference vector, V1 - V2.
C
C$ Detailed_Input
C
C     V1      is a double precision vector of arbitrary dimension which
C             is the minuend (i.e. first or left-hand member) in the
C             vector subtraction.
C
C     V2      is a double precision vector of arbitrary dimension which
C             is the subtrahend (i.e. second or right-hand member) in
C             the vector subtraction.
C
C     NDIM    is the dimension of V1 and V2 (and VOUT).
C
C$ Detailed_Output
C
C     VOUT    is a double precision vector containing the difference
C             V1 - V2.
C
C$ Parameters
C
C     None.
C
C$ Exceptions
C
C     Error free.
C
C$ Files
C
C     None.
C
C$ Particulars
C
C     For each value of the index I from 1 to NDIM, this subroutine
C     performs the following subtraction:
C
C        VOUT(I) = V1(I) - V2(I)
C
C     No error checking is performed to guard against numeric overflow
C     or underflow.
C
C$ Examples
C
C     The following table shows the results of VSUBG from various
C     inputs.
C
C        V1                V2             NDIM         VOUT
C        -----------------------------------------------------------
C        (1, 2, 3, 4)     ( 1, 1, 1, 1 )    4         ( 0, 1, 2, 3 )
C        (1, 2, 3, 4)     (-1,-2,-3,-4 )    4         ( 2, 4, 6, 8 )
C        (1, 2, 3, 4)     (-1, 2,-3, 4 )    4         ( 2, 0, 6, 0 )
C
C$ Restrictions
C
C     No error checking is performed to guard against numeric overflow.
C     The programmer is thus required to insure that the values in V1
C     and V2 are reasonable and will not cause overflow.
C
C$ Literature_References
C
C     None.
C
C$ Author_and_Institution
C
C     W.M. Owen       (JPL)
C
C$ Version
C
C-    SPICELIB Version 1.0.3, 23-APR-2010 (NJB)
C
C        Header correction: assertions that the output
C        can overwrite the input have been removed.
C
C-    SPICELIB Version 1.0.2, 10-MAR-1992 (WLT)
C
C        Comment section for permuted index source lines was added
C        following the header.
C
C-    SPICELIB Version 1.0.1, 9-MAY-1990 (HAN)
C
C        Several errors in the header documentation were corrected.
C
C-    SPICELIB Version 1.0.0, 31-JAN-1990 (WMO)
C
C-&
 
C$ Index_Entries
C
C     n-dimensional vector subtraction
C
C-&
 
      INTEGER I
 
      DO I=1,NDIM
         VOUT(I) = V1(I) - V2(I)
      END DO
 
      RETURN
      END
