
Example()

Func Example()

	Sleep(5000)
	send("{TAB 9}") 
	;send("{ENTER}")
	ControlClick("Policy Information - Internet Explorer","AVPageView","AVL_AVView26")
	send("{CTRLDOWN}")
	send("{SHIFTDOWN}")
	send("{s down}")
	WinWaitActive("Save As")
	send("{CTRLUP}")
	send("{SHIFTUP}")
	send("{s up}")
	ControlFocus("Save As","","Edit1")
	ControlSend("Save As","","Edit1","C:\savePDF\verifyPDF.pdf");
	ControlClick("Save As","&Save","Button1")
	Sleep(3000)
	if WinExists("Confirm Save As")then
		ControlClick("Confirm Save As","&Yes","Button1")
	else 
		send("{TAB}")
	EndIf
	Sleep(2000)
	send("{TAB 8}") 
	send("{ENTER}")
	; Wait 10 seconds for the Run dialogue window to appear.
EndFunc   ;==>Example