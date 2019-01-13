<hr>
<h1 style="color: #FF0000">This project is DEAD and NOT SUPPORTED anymore by its former developer.</h1>
Please fork it and create a new plugin from it. I can guide you through the process (as time allows), but I won't write code, accept pull-requests nor put updates to the NetBeans plugins center.
<hr>

QuickOpener-NetBeans
====================

* [Wiki](https://github.com/dzsessona/QuickOpener-NetBeans/wiki/Home)<br/>
* [Download from plugin center, < NB8.1 ](http://plugins.netbeans.org/plugin/43217/quickopener)
* [Download from plugin center, &ge; NB8.1](http://plugins.netbeans.org/plugin/62668/?show=true)

Sometimes while programming in NetBeans you want to explore a particular file that you are editing on the file system browser, or maybe launch a command in a terminal to do something with it.

![Plugin toolbar](https://raw.githubusercontent.com/dzsessona/QuickOpener-NetBeans/master/QuickOpener/qoscreenshots/shot2.png)

This plugins brings to your NetBeans six action, three of them always available and three of them available when the selected node has a file associated with it. In particular:

_When the selection has a valid file:_

* **Open the default OS shell** on the location of the file (or its folder) selected.
![icon](https://raw.githubusercontent.com/dzsessona/QuickOpener-NetBeans/master/QuickOpener/qoscreenshots/shot7.PNG)&nbsp; 
* **Open the file system browser** on the location of the file (or its folder) selected.
![icon](https://raw.githubusercontent.com/dzsessona/QuickOpener-NetBeans/master/QuickOpener/qoscreenshots/shot8.png)&nbsp; 
* **Copy to the clipboard** the path of the file selected.
![icon](https://raw.githubusercontent.com/dzsessona/QuickOpener-NetBeans/master/QuickOpener/qoscreenshots/shot9.PNG)&nbsp; 

_Always enabled:_

* **Launch a shell command** (with parameters, customizable on preferences) ![icon](https://raw.githubusercontent.com/dzsessona/QuickOpener-NetBeans/master/QuickOpener/qoscreenshots/launch.png)&nbsp; 
* **FileSystem browser on any location** (favorites, customizable on preferences)
![icon](https://raw.githubusercontent.com/dzsessona/QuickOpener-NetBeans/master/QuickOpener/qoscreenshots/shot10.png)&nbsp; 
* **Open a shell on any location** (favorites, customizable on preferences)
![icon](https://raw.githubusercontent.com/dzsessona/QuickOpener-NetBeans/master/QuickOpener/qoscreenshots/shot11.png)&nbsp; 

<h2>Updates</h2>

<h3>1.2.0:</h3>
<ul>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/pull/86">Feature 86</a>]: Support Mate-terminal and Caja-/Space-file manager via configurator (Ubuntu Mate) (PR by javatlacati)</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/pull/84">Task 84</a>]: Fix line-endings and update to JDK7 (PR by javatlacati)</li>
</ul>

<h3>1.1.0:</h3>
<ul>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/66">Feature 66</a>]: Refactor run custom dialog: simplify, add keyboard-only support</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/33">Feature 33</a>]: Support for project folder replacement variable ${projectFolder}/${mainProjectFolder}</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/67">Feature 67</a>]: More patterns - see <a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/67">details</a></li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/30">Feature 30</a>]: Support favorites nodes</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/64">Feature 64</a>]: Support lookups with java.io.File</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/65">Bugfix 65</a>]: Missing mnemonics in dialogs and options</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/69">Bugfix 69</a>]: "Launch custom command..." should be available if there is no selected file</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/77">Bugfix 77</a>]: Fix GUI detection for Ubuntu 16.04</li>
</ul>

<h3>1.0.4:</h3>
<ul>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/63">Bugfix 63</a>]: File manager opens twice (KDE)</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/62">Bugfix 62</a>]: java.lang.NoSuchMethodError: java.lang.Process.waitFor running NB @ JDK7</li>
 </ul>

<h3>1.0.3:</h3>
<ul>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/56">Task 56</a>]: Provide a 8.1 version for the plugin center</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/60">Task 60</a>]: Include new version of oscommands</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/61">Task 61</a>]: Convert to maven-project for better maintainence</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/51">Bugfix 51</a>]: [Windows] Open in FileManager should select the file/dir</li>
<li>[<a href="https://github.com/dzsessona/QuickOpener-NetBeans/issues/49">Bugfix 49</a>]: [Linux] Crash in options when running "LinuxUnknown" and KDE not detected</li>

 </ul>

<p><a href="https://github.com/kinkadzs/QuickOpener-NetBeans/wiki/Home">The project is hosted on github, click here to report a bug or make a suggestion...
    </a>Enjoy, Diego+Benno.
    </p>
