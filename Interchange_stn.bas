Type=StaticCode
Version=6.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	Dim lng As Int
End Sub

Public Sub Encryption_Query_String_stn(t As String) As String

	Dim txt As String

	Dim tx1 As String = ""
	tx1 = ""
	Dim cec(t.Length) As String
	txt = t

	For i = 0 To t.Length - 1
		Dim x As String
		x = txt.Substring(txt.Length - 1)
		cec(i) = sina_ENC_Query_String_stn(x)
		txt = txt.SubString2(0, txt.Length - 1)
	Next
	tx1 = ""
	For j = 0 To cec.Length - 1
		tx1 = cec(j) & tx1

	Next

	'''''''''''''''''''''''''''''''''''''''''''''''''''''''
	Return tx1
End Sub



Public Sub sina_ENC_Query_String_stn(v As String) As String

	If v="0" Then
		Return "wW"
	Else If v="1" Then
		Return "0A"
	Else If v="2" Then
		Return "LI"
	Else If v="3" Then
		Return "p5"
	Else If v="4" Then
		Return "Yf"
	Else If v="5" Then
		Return "9s"
	Else If v="7" Then
		Return "QD"
	Else If v="8" Then
		Return "1Y"
	Else If v="9" Then
		Return "vy"
	Else If v="a" Then
		Return "ZS"
	Else If v="b" Then
		Return "7P"
	Else If v="c" Then
		Return "NM"
	Else If v="d" Then
		Return "uO"
	Else If v="e" Then
		Return "W9"
	Else If v="f" Then
		Return "N2"
	Else If v="g" Then
		Return "co"
	Else If v="h" Then
		Return "bb"
	Else If v="i" Then
		Return "fb"
	Else If v="j" Then
		Return "ru"
	Else If v="k" Then
		Return "fw"
	Else If v="l" Then
		Return "gM"
	Else If v="m" Then
		Return "Ui"
	Else If v="n" Then
		Return "4b"
	Else If v="o" Then
		Return "WF"
	Else If v="p" Then
		Return "OT"
	Else If v="q" Then
		Return "sf"
	Else If v="r" Then
		Return "Ri"
	Else If v="s" Then
		Return "DN"
	Else If v="t" Then
		Return "TS"
	Else If v="u" Then
		Return "Il"
	Else If v="v" Then
		Return "2e"
	Else If v="w" Then
		Return "qF"
	Else If v="x" Then
		Return "GT"
	Else If v="y" Then
		Return "S7"
	Else If v="z" Then
		Return "Zf"
	Else If v="A" Then
		Return "1q"
	Else If v="B" Then
		Return "LY"
	Else If v="C" Then
		Return "2o"
	Else If v="D" Then
		Return "TD"
	Else If v="E" Then
		Return "76"
	Else If v="F" Then
		Return "Cm"
	Else If v="G" Then
		Return "9T"
	Else If v="H" Then
		Return "mo"
	Else If v="I" Then
		Return "5L"
	Else If v="J" Then
		Return "9Y"
	Else If v="K" Then
		Return "VE"
	Else If v="L" Then
		Return "cR"
	Else If v="M" Then
		Return "lX"
	Else If v="N" Then
		Return "2Z"
	Else If v="O" Then
		Return "3x"
	Else If v="P" Then
		Return "pz"
	Else If v="Q" Then
		Return "kg"
	Else If v="R" Then
		Return "oP"
	Else If v="S" Then
		Return "3F"
	Else If v="T" Then
		Return "3E"
	Else If v="U" Then
		Return "ST"
	Else If v="V" Then
		Return "pV"
	Else If v="W" Then
		Return "Zg"
	Else If v="X" Then
		Return "s4"
	Else If v="Y" Then
		Return "Ah"
	Else If v="Z" Then
		Return "bH"
	Else If v="~" Then
		Return "tP"
	Else If v="`" Then
		Return "qw"
	Else If v="!" Then
		Return "c7"
	Else If v="#" Then
		Return "oh"
	Else If v="$" Then
		Return "9b"
	Else If v="%" Then
		Return "q0"
	Else If v="^" Then
		Return "lW"
	Else If v="&" Then
		Return "su"
	Else If v="*" Then
		Return "bS"
	Else If v="(" Then
		Return "0p"
	Else If v=")" Then
		Return "GC"
	Else If v="_" Then
		Return "bc"
	Else If v="-" Then
		Return "YA"
	Else If v="=" Then
		Return "4i"
	Else If v="+" Then
		Return "N9"
	Else If v="/" Then
		Return "i8"
	Else If v="?" Then
		Return "xK"
	Else If v="<" Then
		Return "I3"
	Else If v=">" Then
		Return "2J"
	Else If v="." Then
		Return "Nc"
	Else If v="{" Then
		Return "m7"
	Else If v="}" Then
		Return "0J"
	Else If v="[" Then
		Return "7S"
	Else If v="]" Then
		Return "QK"
	Else If v="," Then
		Return "1f"
	Else If v="'" Then
		Return "PF"
	Else If v="6" Then
		Return "si"
	Else
		Return v & v
	End If
End Sub

'''''''''''''
' Dim lng As Int
'''''''''''''



Public Sub Decryption_Query_String_stn( t As String) As String
	lng =2


	Dim txt As String

	Dim tx2 As String
	Dim cdc(t.Length) As String

	txt = t

	For n = 0 To t.Length / lng - 1
		Dim x As String
		x = txt.Substring(txt.Length - lng)
		cdc(n) = sina_DEC_Query_String_stn(x)
		txt = txt.SubString2(0, txt.Length - lng)
	Next

	For b = 0 To cdc.Length - 1
		tx2 = cdc(b) & tx2
	Next
	''''''''''''''''''''
	Return tx2
End Sub


Public Sub sina_DEC_Query_String_stn(v As String) As String
	If v="wW" Then
		Return "0"
	Else If v="0A" Then
		Return "1"
	Else If v="LI" Then
		Return "2"
	Else If v="p5" Then
		Return "3"
	Else If v="Yf" Then
		Return "4"
	Else If v="9s" Then
		Return "5"
	Else If v="QD" Then
		Return "7"
	Else If v="1Y" Then
		Return "8"
	Else If v="vy" Then
		Return "9"
	Else If v="ZS" Then
		Return "a"
	Else If v="7P" Then
		Return "b"
	Else If v="NM" Then
		Return "c"
	Else If v="uO" Then
		Return "d"
	Else If v="W9" Then
		Return "e"
	Else If v="N2" Then
		Return "f"
	Else If v="co" Then
		Return "g"
	Else If v="bb" Then
		Return "h"
	Else If v="fb" Then
		Return "i"
	Else If v="ru" Then
		Return "j"
	Else If v="fw" Then
		Return "k"
	Else If v="gM" Then
		Return "l"
	Else If v="Ui" Then
		Return "m"
	Else If v="4b" Then
		Return "n"
	Else If v="WF" Then
		Return "o"
	Else If v="OT" Then
		Return "p"
	Else If v="sf" Then
		Return "q"
	Else If v="Ri" Then
		Return "r"
	Else If v="DN" Then
		Return "s"
	Else If v="TS" Then
		Return "t"
	Else If v="Il" Then
		Return "u"
	Else If v="2e" Then
		Return "v"
	Else If v="qF" Then
		Return "w"
	Else If v="GT" Then
		Return "x"
	Else If v="S7" Then
		Return "y"
	Else If v="Zf" Then
		Return "z"
	Else If v="1q" Then
		Return "A"
	Else If v="LY" Then
		Return "B"
	Else If v="2o" Then
		Return "C"
	Else If v="TD" Then
		Return "D"
	Else If v="76" Then
		Return "E"
	Else If v="Cm" Then
		Return "F"
	Else If v="9T" Then
		Return "G"
	Else If v="mo" Then
		Return "H"
	Else If v="5L" Then
		Return "I"
	Else If v="9Y" Then
		Return "J"
	Else If v="VE" Then
		Return "K"
	Else If v="cR" Then
		Return "L"
	Else If v="lX" Then
		Return "M"
	Else If v="2Z" Then
		Return "N"
	Else If v="3x" Then
		Return "O"
	Else If v="pz" Then
		Return "P"
	Else If v="kg" Then
		Return "Q"
	Else If v="oP" Then
		Return "R"
	Else If v="3F" Then
		Return "S"
	Else If v="3E" Then
		Return "T"
	Else If v="ST" Then
		Return "U"
	Else If v="pV" Then
		Return "V"
	Else If v="Zg" Then
		Return "W"
	Else If v="s4" Then
		Return "X"
	Else If v="Ah" Then
		Return "Y"
	Else If v="bH" Then
		Return "Z"
	Else If v="tP" Then
		Return "~"
	Else If v="qw" Then
		Return "`"
	Else If v="c7" Then
		Return "!"
	Else If v="oh" Then
		Return "#"
	Else If v="9b" Then
		Return "$"
	Else If v="q0" Then
		Return "%"
	Else If v="lW" Then
		Return "^"
	Else If v="su" Then
		Return "&"
	Else If v="bS" Then
		Return "*"
	Else If v="0p" Then
		Return "("
	Else If v="GC" Then
		Return ")"
	Else If v="bc" Then
		Return "_"
	Else If v="YA" Then
		Return "-"
	Else If v="4i" Then
		Return "="
	Else If v="N9" Then
		Return "+"
	Else If v="i8" Then
		Return "/"
	Else If v="xK" Then
		Return "?"
	Else If v="I3" Then
		Return "<"
	Else If v="2J" Then
		Return ">"
	Else If v="Nc" Then
		Return "."
	Else If v="m7" Then
		Return "{"
	Else If v="0J" Then
		Return "}"
	Else If v="7S" Then
		Return "["
	Else If v="QK" Then
		Return "]"
	Else If v="1f" Then
		Return ","
	Else If v="PF" Then
		Return "'"
	Else If v="si" Then
		Return "6"
	Else
		Return v.Substring(v.Length - 1)
	End If

End Sub