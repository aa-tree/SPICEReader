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
<p><br/>
<br/>

</p>
<h1 class="western"><span style="font-style: normal">Depedencies</span></h1>
<p><span style="font-style: normal">This project requires:</span></p>
<ol>
	<li/>
<p><span style="font-style: normal">gfortran </span><span style="font-style: normal">[64bit]</span><span style="font-style: normal">
	(Tested with 5.4.0 20160609)</span></p>
	<li/>
<p><span style="font-style: normal">openjdk (Tested with
	openjdk version &quot;1.8.0_222&quot;)</span></p>
</ol>
<h1 class="western"><a name="user-content-installation"></a>Installation</h1>
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
<p style="margin-bottom: 0cm; line-height: 100%">[You can skip this
step, because the SPICE Toolkit is freely available so it will be
available in the repository. If perhaps the license doesn’t allow
free distribution, this file might be removed from the repository.]</p>
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
<span style="font-style: normal">/</span><i>path-to-repo/SPICEReader</i><i>/</i><i>java_base/src/SPICEReader.class</i></p>
<p style="font-style: normal">To get help, navigate to the folder
/<i>path-to-repo/SPICEReader</i><i>/</i><i>java_base/src/</i> and
execute:</p>
<p align="center"><i>java src.SPICEReader -help</i></p>
<p align="center" style="font-style: normal"><b>[Help feature is not
available yet, will be up pretty soon.]</b></p>
