C$Procedure      DSKOBJ ( DSK, get object IDs )
 
      SUBROUTINE DSKOBJ ( DSK, BODIDS )
 
C$ Abstract
C
C     Find the set of body ID codes of all objects for which
C     topographic data are provided in a specified DSK file.
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
C     CELLS
C     DAS
C     DSK
C     SETS
C     
C$ Keywords
C
C     COVERAGE
C     TOPOGRAPHY
C
C$ Declarations
 
      IMPLICIT NONE

      INCLUDE 'dla.inc'
      INCLUDE 'dskdsc.inc'

      INTEGER               LBCELL
      PARAMETER           ( LBCELL = -5 )

      CHARACTER*(*)         DSK
      INTEGER               BODIDS ( LBCELL : * )
 
C$ Brief_I/O
C
C     Variable  I/O  Description
C     --------  ---  --------------------------------------------------
C     DSK        I   Name of DSK file.
C     BODIDS    I-O  Set of ID codes of objects in DSK file.
C
C$ Detailed_Input
C
C     DSK            is the name of a DSK file. This file will be 
C                    opened for read access by this routine.
C     
C     BODIDS         is an initialized SPICELIB set data structure. 
C
C                    BODIDS optionally may contain a set of body ID
C                    codes on input; on output, the data already
C                    present in BODIDS will be combined with ID code
C                    set found for the file DSK.
C
C                    If BODIDS contains no data on input, its size and
C                    cardinality still must be initialized.
C
C$ Detailed_Output
C
C     BODIDS         is a SPICELIB set data structure that contains the
C                    union of its contents upon input with the set of
C                    body ID codes of segments in the indicated DSK
C                    file.
C
C                    The elements of SPICELIB sets are unique; each ID
C                    code in BODIDS appears only once, even if the DSK
C                    file contains multiple segments for that ID code.
C
C                    See the Examples section below for a complete
C                    example program showing how to retrieve the body
C                    and surface ID codes from a DSK file.
C                                      
C$ Parameters
C
C     None.
C
C$ Exceptions
C
C     1)  If the input file has transfer format, the error 
C         SPICE(INVALIDFORMAT) is signaled.
C
C     2)  If the input file is not a transfer file but has architecture
C         other than DAS, the error SPICE(BADARCHTYPE) is signaled.
C
C     3)  If the input file is a binary DAS file of type other than
C         DSK, the error SPICE(BADFILETYPE) is signaled.
C
C     4)  If the DSK file cannot be opened or read, the error will
C         be diagnosed by routines called by this routine.
C
C     5)  If the size of the output set argument BODIDS is insufficient
C         to contain the actual number of ID codes of objects covered
C         by the indicated DSK file, the error will be diagnosed by
C         routines called by this routine.
C
C$ Files
C
C     See the description of the argument DSK above.
C
C$ Particulars
C
C     This routine provides an API via which applications can determine
C     the set of objects for which there are topographic data in a
C     specified DSK file.
C
C$ Examples
C
C
C     The formatting of the results shown for this example may differ
C     across platforms.
C
C
C     1)  Display the coverage for each object in a specified DSK file.
C         Find the set of objects in the file. Loop over the contents
C         of the ID code set: find the surface ID for each item in the
C         set and display the surface ID.
C
C
C     Example code begins here.
C
C
C        C
C        C     Examine a DSK file and identify the set of
C        C     central bodies associated with the segments
C        C     in the file. For each body, find the
C        C     set of surfaces associated with that body.
C        C
C              PROGRAM EX1
C              IMPLICIT NONE
C        C
C        C     SPICELIB functions
C        C
C              INTEGER               CARDI
C        C
C        C     Local parameters
C        C
C              INTEGER               LBCELL
C              PARAMETER           ( LBCELL = -5 )
C
C              INTEGER               FILSIZ
C              PARAMETER           ( FILSIZ = 255 )
C
C              INTEGER               MAXID
C              PARAMETER           ( MAXID  = 10000 )
C        C
C        C     Local variables
C        C
C              CHARACTER*(FILSIZ)    DSK
C
C              INTEGER               BODIDS ( LBCELL : MAXID )
C              INTEGER               I
C              INTEGER               J
C              INTEGER               SRFIDS ( LBCELL : MAXID )
C
C        C
C        C     Initialize body ID and surface ID cells.
C        C
C              CALL SSIZEI ( MAXID, BODIDS )
C              CALL SSIZEI ( MAXID, SRFIDS )
C
C        C
C        C     Prompt for the name of a DSK file.
C        C
C              CALL PROMPT ( 'Enter name of DSK file > ', DSK )
C
C        C
C        C     Obtain body ID set for the DSK.
C        C
C              CALL DSKOBJ ( DSK, BODIDS )
C
C              DO I = 1, CARDI( BODIDS )
C
C                 WRITE (*,*) ' '
C                 WRITE (*,*) 'Body ID:     ', BODIDS(I)
C        C
C        C        Get the surface IDs for the Ith body.
C        C
C                 CALL DSKSRF ( DSK, BODIDS(I), SRFIDS )
C
C                 DO J = 1, CARDI( SRFIDS )
C                    WRITE (*,*) '   Surface ID: ', SRFIDS(J)
C                 END DO
C
C              END DO
C
C              END
C
C
C     When this program was executed on a PC/Linux/gfortran/64-bit
C     platform, using as input the name of a DSK created by a code
C     example in the header of DSKW02, the output was:
C
C
C        Enter name of DSK file > phobos_3_3_3seg.bds
C
C         Body ID:              401
C            Surface ID:            1
C            Surface ID:            2
C            Surface ID:            3 
C
C
C$ Restrictions
C
C     1) If an error occurs while this routine is updating the set
C        BODIDS, the set may be corrupted.
C
C$ Literature_References
C
C     None.
C
C$ Author_and_Institution
C
C     N.J. Bachman   (JPL)
C
C$ Version
C
C-    SPICELIB Version 1.0.0, 22-AUG-2016 (NJB)
C
C-&
 
C$ Index_Entries
C
C     find id codes of ephemeris objects in dsk file
C     find id codes of bodies in dsk file
C
C-&
 

C
C     SPICELIB functions
C
      INTEGER               CARDI
      INTEGER               SIZEI

      LOGICAL               FAILED
      LOGICAL               RETURN

C
C     Local parameters
C
      INTEGER               TOKLEN
      PARAMETER           ( TOKLEN = 4 )

C
C     Local variables
C
      CHARACTER*(TOKLEN)    ARCH
      CHARACTER*(TOKLEN)    KERTYP

      DOUBLE PRECISION      DSKDSC ( DSKDSZ )

      INTEGER               BID
      INTEGER               DLADSC ( DLADSZ )
      INTEGER               HANDLE
      INTEGER               NXTDSC ( DLADSZ )

      LOGICAL               FOUND

      
      IF ( RETURN() ) THEN
         RETURN
      END IF

      CALL CHKIN ( 'DSKOBJ' )

C
C     See whether GETFAT thinks we've got a DSK file.
C
      CALL GETFAT ( DSK, ARCH, KERTYP )

      IF ( ARCH .EQ. 'XFR' ) THEN

         CALL SETMSG ( 'Input file # has architecture #. The file ' 
     .   //            'must be a binary DSK file to be readable '  
     .   //            'by this routine. If the input file is an ' 
     .   //            'DSK file in transfer format, run TOBIN on ' 
     .   //            'the file to convert it to binary format.'  )
         CALL ERRCH  ( '#',  DSK                                   )
         CALL ERRCH  ( '#',  ARCH                                  )
         CALL SIGERR ( 'SPICE(INVALIDFORMAT)'                      )
         CALL CHKOUT ( 'DSKOBJ'                                    )
         RETURN

      ELSE IF ( ARCH .NE. 'DAS' ) THEN

         CALL SETMSG ( 'Input file # has architecture #. The file ' 
     .   //            'must be a binary DSK file to be readable '  
     .   //            'by this routine. Binary DSK files have '   
     .   //            'DAS architecture. If you expected the '    
     .   //            'file to be a binary DSK file, the problem ' 
     .   //            'may be due to the file being an old '       
     .   //            'non-native file lacking binary file format '
     .   //            'information. It''s also possible the file ' 
     .   //            'has been corrupted.'                       )
         CALL ERRCH  ( '#',  DSK                                   )
         CALL ERRCH  ( '#',  ARCH                                  )
         CALL SIGERR ( 'SPICE(INVALIDARCHTYPE)'                    )
         CALL CHKOUT ( 'DSKOBJ'                                    )
         RETURN

      ELSE IF ( KERTYP .NE. 'DSK' ) THEN

         CALL SETMSG ( 'Input file # has file type #. The file ' 
     .   //            'must be a binary DSK file to be readable '  
     .   //            'by this routine. If you expected the '      
     .   //            'file to be a binary DSK file, the problem ' 
     .   //            'may be due to the file being an old '       
     .   //            'non-native file lacking binary file format '
     .   //            'information. It''s also possible the file ' 
     .   //            'has been corrupted.'                       )
         CALL ERRCH  ( '#',  DSK                                   )
         CALL ERRCH  ( '#',  KERTYP                                )
         CALL SIGERR ( 'SPICE(INVALIDFILETYPE)'                    )
         CALL CHKOUT ( 'DSKOBJ'                                    )
         RETURN

      END IF

C
C     Open the DSK for read access; start a forward search.
C
      CALL DASOPR ( DSK,    HANDLE )
      CALL DLABFS ( HANDLE, NXTDSC, FOUND )

      IF ( FAILED() ) THEN
         CALL CHKOUT ( 'DSKOBJ' )
         RETURN
      END IF
      
      DO WHILE (  FOUND  .AND.  ( .NOT. FAILED() )  )
C
C        Get the DSK descriptor of the current segment.
C        This is where we'll find the body ID code.
C
         CALL MOVEI ( NXTDSC, DLADSZ, DLADSC )
         
         CALL DSKGD ( HANDLE, DLADSC, DSKDSC )
C
C        The body ID is at location CTRIDX ("center index")
C        of the DSK descriptor.
C
C        Append, rather than insert, the new ID. We'll
C        turn the cell into a set at the end of the loop.
C
C        Before appending, make sure there's room in the cell
C        for another entry. We can't afford to let APPNDI 
C        catch an out-of-room error, because we would lose
C        the ability to close the file.
C        
         BID = NINT( DSKDSC(CTRIDX) )

         IF ( CARDI(BODIDS) .EQ. SIZEI(BODIDS) ) THEN
C
C           We're going to signal an error. Close the DSK 
C           first.
C
            CALL DSKCLS ( HANDLE, .FALSE. )

            CALL SETMSG ( 'Cannot append body ID # to cell '
     .      //            'while reading DSK file #. '
     .      //            'Cell size is #.'                 )
            CALL ERRINT ( '#', BID                          )
            CALL ERRCH  ( '#', DSK                          )
            CALL ERRINT ( '#', SIZEI( BODIDS )              )
            CALL SIGERR ( 'SPICE(CELLTOOSMALL)'             )
            CALL CHKOUT ( 'DSKOBJ'                          )
            RETURN

         END IF

         CALL APPNDI ( BID, BODIDS )
C
C        Fetch the DLA descriptor of the next segment.
C
         CALL DLAFNS ( HANDLE, DLADSC, NXTDSC, FOUND )

      END DO

      CALL VALIDI ( SIZEI(BODIDS), CARDI(BODIDS), BODIDS )
     
      CALL DASCLS ( HANDLE )

      CALL CHKOUT ( 'DSKOBJ' )
      RETURN
      END
