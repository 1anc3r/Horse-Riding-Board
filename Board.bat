@echo off
if "%1"=="h" goto go
mshta vbscript:createobject("wscript.shell").run(""%0"h",0)(window.close)&exit
:go
java Board