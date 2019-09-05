<h1 class="western"><a name="user-content-introduction"></a>Introduction</h1>
<p>This project provides a Java frontend for accessing JPL's SPICE
(<a href="https://naif.jpl.nasa.gov/naif/">https://naif.jpl.nasa.gov/naif/</a>).
The aim is to have both a command line interface and a graphical
interface.</p>
<p><br/>
<br/>

</p>
<h1 class="western"><a name="user-content-structure"></a>Structure</h1>
<p><br/>
<br/>

</p>
<p>The whole project consists of two parts, FORTRAN backend, and a
Java CLI and GUI. The FORTRAN end is located in the folder
<i>fortran_src</i><span style="font-style: normal"> and the Java code
in </span><i>java_base</i><span style="font-style: normal">.</span></p>
<p><span style="font-style: normal">The FORTRAN backend has routines
for accessing the functions of SPICELib’s toolkits and some other
routines like N-body problem in astrodynamics and RK89 integrator.
The FORTRAN backend can be used independently as well.  </span><span style="font-style: normal">Docs
for that would be coming up soon.</span></p>
<p style="font-style: normal"><br/>
<br/>

</p>
<h1 class="western" style="font-style: normal">Dependencies</h1>
<p style="font-style: normal">This project requires:</p>
<ol>
	<li/>
<p><span style="font-style: normal">gfortran </span><span style="font-style: normal">[64bit]</span><span style="font-style: normal">
	(Tested with 5.4.0 20160609)</span></p>
	<li/>
<p style="font-style: normal">openjdk (Tested with openjdk
	version &quot;1.8.0_222&quot;)</p>
</ol>
<h1 class="western"><a name="user-content-installation"></a>Installation</h1>
<p style="margin-bottom: 0cm; line-height: 100%">Note: These steps
will be automated in upcoming version of the install script.</p>
<p style="margin-bottom: 0cm; line-height: 100%"><br/>

</p>
<ol>
	<li/>
<p>Download this repository. Let's say you store it in
	<i>/path-to-repo/SPICEReader</i></p>
	<li/>
<p> Download JPL's SPICELIB (FORTRAN) from here:
	<a href="http://naif.jpl.nasa.gov/pub/naif/toolkit//FORTRAN/PC_Linux_gfortran_64bit/packages/toolkit.tar.Z">http://naif.jpl.nasa.gov/pub/naif/toolkit//FORTRAN/PC_Linux_gfortran_64bit/packages/toolkit.tar.Z</a></p>
	<ol type="a">
		<li/>
<p>Unzip the downloaded file. Let's say you get a folder like
		<i>/path-to-SPICE-ZIP/toolkit</i></p>
		<li/>
<p>Run '<i>makeall.csh'. </i>
		</p>
		<li/>
<p><i>Copy the file /path-to-SPICE-ZIP/toolkit/lib/spicelib.a
		to /path-to-repo/SPICEReader/fortran_src/toolkit/lib/</i></p>
	</ol>
</ol>
<p style="margin-bottom: 0cm; line-height: 100%"><br/>

</p>
<ol start="3">
	<li/>
<p style="margin-bottom: 0cm; line-height: 100%">Download the
	following Binary kernels from JPL’s website. These are data files
	that store the ephemerides. Use FTP, to make this process simple.</p>
	<ol type="a">
		<li/>
<p style="margin-bottom: 0cm; line-height: 100%"><a href="https://naif.jpl.nasa.gov/pub/naif/generic_kernels/spk/planets/de435.bsp">https://naif.jpl.nasa.gov/pub/naif/generic_kernels/spk/planets/de435.bsp</a></p>
		<li/>
<p style="margin-bottom: 0cm; line-height: 100%">All files in
		the folder
		<a href="https://naif.jpl.nasa.gov/pub/naif/generic_kernels/spk/satellites/">https://naif.jpl.nasa.gov/pub/naif/generic_kernels/spk/satellites/</a><br/>
<br/>
Skip
		the folder <i>a_old_version</i><span style="font-style: normal">.</span></p>
	</ol>
</ol>
<p style="margin-bottom: 0cm; line-height: 100%"><br/>

</p>
<p style="margin-bottom: 0cm; line-height: 100%">		Move all these
downloaded files to  <i>/path-to-repo/SPICEReader</i><i>/fortran_src/eph/</i></p>
<p style="margin-bottom: 0cm; line-height: 100%"><br/>

</p>
<ol start="4">
	<li/>
<p style="margin-bottom: 0cm; line-height: 100%"><span style="font-style: normal">Download
	all the files in this folder from
	here:<br/>
</span><i><a href="https://naif.jpl.nasa.gov/pub/naif/generic_kernels/lsk/">https://naif.jpl.nasa.gov/pub/naif/generic_kernels/lsk/</a><br/>
<br/>
</i><span style="font-style: normal">Move
	these to /</span><i>path-to-repo/SPICEReader</i><i>/fortran_src/</i><i>gkernels</i></p>
	<p style="margin-bottom: 0cm; line-height: 100%"></p>
	<li/>
<p style="margin-bottom: 0cm; font-style: normal; line-height: 100%">
	Run the script /<i>path-to-repo/SPICEReader</i><i>/</i><i>install.sh</i></p>
	<p style="margin-bottom: 0cm; line-height: 100%"></p>
</ol>
<p style="margin-bottom: 0cm; line-height: 100%"><br/>

</p>
<h1 class="western">Execution</h1>
<p><br/>
<br/>

</p>
<p>The primary program to execute is
<span style="font-style: normal">/</span><i>path-to-repo/SPICEReader</i><i>/</i><i>SPICEReader.</i><i>sh</i></p>
<p style="font-style: normal"><br/>
<br/>

</p>
<p style="font-style: normal">To get help, execute</p>
<p align="center" style="font-style: normal">sh <span style="font-weight: normal">SPICEReader.sh
--help</span></p>
<p align="center" style="font-style: normal"><br/>
<br/>

</p>
