@echo off / waiting...
@Rem cd mir
c:
cd C:\Program Files\OpenOffice.org 3\program
@Rem run the socket 8100 and host 127.0.0.1
soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;"  -headless -nofirststartwizard
@Rem look the socket is run...
@Rem netstat -an
@Rem exit bat
exit