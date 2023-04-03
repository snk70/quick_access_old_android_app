Type=Class
Version=6.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Class module
Sub Class_Globals
	Dim txt,c,e,d,l As String
End Sub

Public Sub Initialize

End Sub

Public Sub enc(t As String) As String
Dim tx1 As String=""
tx1=""
Dim cec(t.Length) As String
txt=t

		For i=0 To t.Length-1
			Dim x As String
			x=txt.SubString(txt.Length-1)
			cec(i)=modernenc(x)
			txt=txt.SubString2(0,txt.Length-1)
		Next
tx1=""
For j=0 To cec.Length-1
tx1=cec(j)&tx1

Next

'''''''''''''''''''''''''''''''''''''''''''''''''''''''
Return tx1
End Sub

Public Sub dec(t As String) As String
Dim tx2 As String
Dim cdc(t.Length) As String

txt=t

			For n=0 To t.Length/3-1
			Dim x As String
			x=txt.SubString(txt.Length-3)
			cdc(n)=moderndec(x)
			txt=txt.SubString2(0,txt.Length-3)
			Next
			
	For b=0 To cdc.Length-1
	tx2=cdc(b)&tx2
	Next
''''''''''''''''''''
Return tx2
End Sub


Public Sub List_Dec(MyList As List) As List
	Dim lst1 As List
	lst1.Initialize
	
	For i=0 To MyList.Size-1
		Dim str As String=MyList.Get(i)
		lst1.Add(dec(str))
	Next
	Return lst1
End Sub

Public Sub List_Enc(MyList As List) As List
	Dim lst1 As List
	lst1.Initialize
	
	For i=0 To MyList.Size-1
		Dim str As String=MyList.Get(i)
		lst1.Add(enc(str))
	Next
	Return lst1
End Sub

''''''''''''''''''''''''''''''''''''''''''''''
Private Sub modernenc(v As String) As String
If v="0" Then
Return "746"
Else If v="1" Then
Return "888"
Else If v="2" Then
Return "174"
Else If v="3" Then
Return "972"
Else If v="4" Then
Return "579"
Else If v="5" Then
Return "572"
Else If v="7" Then
Return "268"
Else If v="8" Then
Return "117"
Else If v="9" Then
Return "236"
Else If v="a" Then
Return "543"
Else If v="b" Then
Return "991"
Else If v="c" Then
Return "732"
Else If v="d" Then
Return "699"
Else If v="e" Then
Return "415"
Else If v="f" Then
Return "675"
Else If v="g" Then
Return "845"
Else If v="h" Then
Return "591"
Else If v="i" Then
Return "349"
Else If v="j" Then
Return "250"
Else If v="k" Then
Return "584"
Else If v="l" Then
Return "643"
Else If v="m" Then
Return "300"
Else If v="n" Then
Return "822"
Else If v="o" Then
Return "635"
Else If v="p" Then
Return "553"
Else If v="q" Then
Return "177"
Else If v="r" Then
Return "133"
Else If v="s" Then
Return "788"
Else If v="t" Then
Return "347"
Else If v="u" Then
Return "140"
Else If v="v" Then
Return "166"
Else If v="w" Then
Return "458"
Else If v="x" Then
Return "243"
Else If v="y" Then
Return "323"
Else If v="z" Then
Return "194"
Else If v="A" Then
Return "471"
Else If v="B" Then
Return "138"
Else If v="C" Then
Return "296"
Else If v="D" Then
Return "970"
Else If v="E" Then
Return "477"
Else If v="F" Then
Return "631"
Else If v="G" Then
Return "738"
Else If v="H" Then
Return "714"
Else If v="I" Then
Return "507"
Else If v="J" Then
Return "157"
Else If v="K" Then
Return "124"
Else If v="L" Then
Return "102"
Else If v="M" Then
Return "218"
Else If v="N" Then
Return "259"
Else If v="O" Then
Return "961"
Else If v="P" Then
Return "603"
Else If v="Q" Then
Return "404"
Else If v="R" Then
Return "545"
Else If v="S" Then
Return "590"
Else If v="T" Then
Return "346"
Else If v="U" Then
Return "538"
Else If v="V" Then
Return "518"
Else If v="W" Then
Return "502"
Else If v="X" Then
Return "539"
Else If v="Y" Then
Return "478"
Else If v="Z" Then
Return "768"
Else If v="~" Then
Return "196"
Else If v="`" Then
Return "633"
Else If v="!" Then
Return "589"
Else If v="#" Then
Return "476"
Else If v="$" Then
Return "321"
Else If v="%" Then
Return "304"
Else If v="^" Then
Return "807"
Else If v="&" Then
Return "394"
Else If v="*" Then
Return "377"
Else If v="(" Then
Return "771"
Else If v=")" Then
Return "951"
Else If v="_" Then
Return "904"
Else If v="-" Then
Return "585"
Else If v="=" Then
Return "577"
Else If v="+" Then
Return "529"
Else If v="/" Then
Return "913"
Else If v="\" Then
Return "628"
Else If v="?" Then
Return "384"
Else If v="<" Then
Return "535"
Else If v=">" Then
Return "760"
Else If v="." Then
Return "911"
Else If v="{" Then
Return "651"
Else If v="}" Then
Return "975"
Else If v="[" Then
Return "647"
Else If v="]" Then
Return "598"
Else If v="""" Then
Return "777"
Else If v="," Then
Return "568"
Else
Return v&v&v
End If
End Sub

Private Sub moderndec(v As String) As String
If v="746" Then
Return "0"
Else If v="888" Then
Return "1"
Else If v="174" Then
Return "2"
Else If v="972" Then
Return "3"
Else If v="579" Then
Return "4"
Else If v="572" Then
Return "5"
Else If v="268" Then
Return "7"
Else If v="117" Then
Return "8"
Else If v="236" Then
Return "9"
Else If v="543" Then
Return "a"
Else If v="991" Then
Return "b"
Else If v="732" Then
Return "c"
Else If v="699" Then
Return "d"
Else If v="415" Then
Return "e"
Else If v="675" Then
Return "f"
Else If v="845" Then
Return "g"
Else If v="591" Then
Return "h"
Else If v="349" Then
Return "i"
Else If v="250" Then
Return "j"
Else If v="584" Then
Return "k"
Else If v="643" Then
Return "l"
Else If v="300" Then
Return "m"
Else If v="822" Then
Return "n"
Else If v="635" Then
Return "o"
Else If v="553" Then
Return "p"
Else If v="177" Then
Return "q"
Else If v="133" Then
Return "r"
Else If v="788" Then
Return "s"
Else If v="347" Then
Return "t"
Else If v="140" Then
Return "u"
Else If v="166" Then
Return "v"
Else If v="458" Then
Return "w"
Else If v="243" Then
Return "x"
Else If v="323" Then
Return "y"
Else If v="194" Then
Return "z"
Else If v="471" Then
Return "A"
Else If v="138" Then
Return "B"
Else If v="296" Then
Return "C"
Else If v="970" Then
Return "D"
Else If v="477" Then
Return "E"
Else If v="631" Then
Return "F"
Else If v="738" Then
Return "G"
Else If v="714" Then
Return "H"
Else If v="507" Then
Return "I"
Else If v="157" Then
Return "J"
Else If v="124" Then
Return "K"
Else If v="102" Then
Return "L"
Else If v="218" Then
Return "M"
Else If v="259" Then
Return "N"
Else If v="961" Then
Return "O"
Else If v="603" Then
Return "P"
Else If v="404" Then
Return "Q"
Else If v="545" Then
Return "R"
Else If v="590" Then
Return "S"
Else If v="346" Then
Return "T"
Else If v="538" Then
Return "U"
Else If v="518" Then
Return "V"
Else If v="502" Then
Return "W"
Else If v="539" Then
Return "X"
Else If v="478" Then
Return "Y"
Else If v="768" Then
Return "Z"
Else If v="196" Then
Return "~"
Else If v="633" Then
Return "`"
Else If v="589" Then
Return "!"
Else If v="476" Then
Return "#"
Else If v="321" Then
Return "$"
Else If v="304" Then
Return "%"
Else If v="807" Then
Return "^"
Else If v="394" Then
Return "&"
Else If v="377" Then
Return "*"
Else If v="771" Then
Return "("
Else If v="951" Then
Return ")"
Else If v="904" Then
Return "_"
Else If v="585" Then
Return "-"
Else If v="577" Then
Return "="
Else If v="529" Then
Return "+"
Else If v="913" Then
Return "/"
Else If v="628" Then
Return "\"
Else If v="384" Then
Return "?"
Else If v="535" Then
Return "<"
Else If v="760" Then
Return ">"
Else If v="911" Then
Return "."
Else If v="651" Then
Return "{"
Else If v="975" Then
Return "}"
Else If v="647" Then
Return "["
Else If v="598" Then
Return "]"
Else If v="777" Then
Return """"
Else If v="568" Then
Return ","
Else
Return v.SubString(v.Length-1)
End If
End Sub