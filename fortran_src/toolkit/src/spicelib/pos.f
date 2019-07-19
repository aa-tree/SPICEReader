C$Procedure            POS ( Position of substring )
 
      INTEGER FUNCTION POS ( STR, SUBSTR, START )
 
C$ Abstract
C
C     Find the first occurrence in a string of a substring, starting at
C     a specified location, searching forward.
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
C     SCANNING
C
C$ Keywords
C
C     CHARACTER
C     SEARCH
C     UTILITY
C
C$ Declarations
 
      CHARACTER*(*)         STR
      CHARACTER*(*)         SUBSTR
      INTEGER               START
 
C$ Brief_I/O
C
C     VARIABLE  I/O  DESCRIPTION
C     --------  ---  --------------------------------------------------
C     STR        I   A character string
C     SUBSTR     I   Substring to locate in the character string.
C     START      I   Where to start looking for SUBSTR in STR.
C
C     The function returns the index of SUBSTR in STR following START
C
C$ Detailed_Input
C
C     STR        is any character string.
C
C     SUBSTR     is a substring to look for in STR.  Spaces in
C                SUBSTR are significant.
C
C     START      is the position in STR to begin looking for SUBSTR.
C
C$ Detailed_Output
C
C     The function returns the index of the beginning of the first
C     substring of STR that begins on or after index START and is equal
C     to SUBSTR. If the substring cannot be found after START, the
C     function is returns 0.
C
C$ Parameters
C
C     None.
C
C$ Exceptions
C
C     Error Free.
C
C     1) If START is less than 1, the search begins at the first
C        character of the string.
C
C     2) If START is greater than the length of the string, POS
C        returns zero.
C
C$ Files
C
C     None.
C
C$ Particulars
C
C     POS is case sensitive.
C
C     An entire family of related SPICELIB routines (POS, CPOS, NCPOS,
C     POSR, CPOSR, NCPOSR) is described in the Required Reading.
C
C     Those familiar with the True BASIC language should note that
C     these functions are equivalent to the True BASIC intrinsic
C     functions with the same names.
C
C$ Examples
C
C     Let STRING = 'AN ANT AND AN ELEPHANT        '
C                   123456789012345678901234567890
C
C      Normal (Sequential) Searching:
C      ------------------------------
C
C            POS ( STRING, 'AN',  1 ) =  1
C            POS ( STRING, 'AN',  3 ) =  4
C            POS ( STRING, 'AN',  6 ) =  8
C            POS ( STRING, 'AN', 10 ) = 12
C            POS ( STRING, 'AN', 14 ) = 20
C            POS ( STRING, 'AN', 22 ) =  0
C
C      START out of bounds:
C      --------------------
C
C            POS ( STRING, 'AN', -5 ) =  1
C            POS ( STRING, 'AN',  0 ) =  1
C            POS ( STRING, 'AN', 31 ) =  0
C            POS ( STRING, 'AN', 44 ) =  0
C
C      Significance of Spaces:
C      -----------------------
C
C            POS ( STRING, 'AN',    1 ) =  1
C            POS ( STRING, ' AN',   1 ) =  3
C            POS ( STRING, ' AN ',  1 ) = 11
C            POS ( STRING, ' AN  ', 1 ) =  0
C
C$ Restrictions
C
C     None.
C
C$ Literature_References
C
C     None.
C
C$ Author_and_Institution
C
C     W.L. Taber      (JPL)
C     H.A. Neilan     (JPL)
C     B.V. Semenov    (JPL)
C
C$ Version
C
C-    SPICELIB Version 1.0.3, 31-JAN-2008 (BVS)
C
C        Removed non-standard end-of-declarations marker
C        'C%&END_DECLARATIONS' from comments.
C
C-    SPICELIB Version 1.0.2, 10-MAR-1992 (WLT)
C
C        Comment section for permuted index source lines was added
C        following the header.
C
C-    SPICELIB Version 1.0.1, 26-MAR-1991 (HAN)
C
C        The Required Reading file POSITION was renamed to SCANNING.
C        This header was updated to reflect the change.
C
C-    SPICELIB Version 1.0.0, 31-JAN-1990 (WLT)
C
C-&
 
C$ Index_Entries
C
C     position of substring
C
C-&
 
 
 
 
C
C     Local variables
C
      LOGICAL               FOUND
      INTEGER               LENSTR
      INTEGER               OFFSET
      INTEGER               LCHNCE
      INTEGER               B
 
 
C
C     Let's find out how big every body is.
C
      LENSTR =  LEN   ( STR                    )
      OFFSET =  MAX   ( 0,  LEN ( SUBSTR ) - 1 )
      LCHNCE =  LENSTR - OFFSET
 
      B      =  MAX ( 1, START )
 
C
C     Look for the string until we run find it or run out of room to
C     look.
C
      FOUND  = .FALSE.
      POS    =  0
 
      DO WHILE ( .NOT. FOUND )
 
         IF ( B .GT. LCHNCE ) THEN
            RETURN
         ELSE IF ( STR(B:B+OFFSET) .EQ. SUBSTR ) THEN
            POS = B
            RETURN
         ELSE
            B   = B + 1
         END IF
 
      END DO
 
      RETURN
      END
