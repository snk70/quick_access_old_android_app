Type=Service
Version=6.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	
#End Region

Sub Process_Globals
	Dim ht_check As HttpJob
	Dim vain_text="ertjerth34%Y#$%TYERtgherlkther8585therthjiop0w-kmgdsf,bwoie033$3wep0jbfrgbdkjdflrtgkjwerngwtb45@#dfnkweregtrbw4tberebwtrbertbrt09ugert9gertgiegmert0g4u,cg4cg5498ut3v54v"
	Dim pi As String="148066"
	Dim rnd_Path As String="rteuiokiu_dsgerg/wergmlret/sgdfsdf/lkmsdferpjjld/Der2343SDF"
	Dim rnd_f As String="sfgjmsert3w4eg.png"
	Dim lsn_Path As String="wertweadfgsdfg/ssdfgsdfg45trvbertgb/34tgefbvsbw5gewevergfwe/sdgwetgerbs5w4vrtgwuj56h/wertwe"
	Dim lsn_f As String="gjbhfdkbdfgbesrwek4rgvpk_ghsdgsg.zip"
	Dim ch_sign As CheckSignature
	Dim WebSite_URL As String="http://modernplus.ir"
	Dim p_sign="market_apps"
	Dim rn As Int
	
	Dim OK_Status As Boolean=True
	
End Sub

Sub Service_Create

End Sub

Sub Service_Start (StartingIntent As Intent)

	
	If File.Exists(File.DirInternal,lsn_Path&"/"&lsn_f)=True And File.Exists(File.DirInternal,rnd_Path&"/"&rnd_f)=True Then
		'تست صحت لایسنس ها
					
		rn=(File.GetText(File.DirInternal,rnd_Path&"/"&rnd_f)-3)/2
		'
		If File.GetText(File.DirInternal,lsn_Path&"/"&lsn_f).Replace(vain_text,"")=Create_HardLicence(rn,pi) Then
			'آغاز کار برنامه
			
		Else
			HandWork_Licence
		End If
					
	End If
	
	
	ht_check.Initialize("ht_check",Me)
	ht_check.PostString(WebSite_URL&"/prv/bdsgsdfgsdfg/dsdfgsdfgsdfgsdfgsdfg/sdfgsdfgsdfgsdfg.php","sina=gh&ya_ali=dfgsdf&gsdfgsdf="&p_sign)


End Sub

Sub Service_Destroy

End Sub


Sub JobDone(Job As HttpJob)
	If Job.Success Then
		If Job.JobName="ht_check" And Job.GetString2("ASCII")<>"" Then
			If Job.GetString2("ASCII").Replace("?","")<>ch_sign.sha1.Replace(":","").ToLowerCase And Job.GetString2("ASCII")<>"" Then
			
				HandWork_Licence
			
			End If
		End If
	End If
End Sub

Sub HandWork_Licence
	ToastMessageShow("لایسنس امنیتی نرم افزار دستکاری شده",True)
	File.Delete(File.DirInternal,lsn_Path&"/"&lsn_f)
	File.Delete(File.DirInternal,rnd_Path&"/"&rnd_f)
	ExitApplication
End Sub



Public Sub Encryption_stn2(t As String) As String

	Dim txt As String

	Dim tx1 As String = ""
	tx1 = ""
	Dim cec(t.Length) As String
	txt = t

	For i = 0 To t.Length - 1
		Dim x As String
		x = txt.Substring(txt.Length - 1)
		cec(i) = sina_ENC_stn2(x)
		txt = txt.SubString2(0, txt.Length - 1)
	Next
	tx1 = ""
	For j = 0 To cec.Length - 1
		tx1 = cec(j) & tx1

	Next

	'''''''''''''''''''''''''''''''''''''''''''''''''''''''
	Return tx1
End Sub



Public Sub sina_ENC_stn2(v As String) As String

	If v="0" Then
		Return "_%"
	Else If v="1" Then
		Return "8i"
	Else If v="2" Then
		Return "-f"
	Else If v="3" Then
		Return "?1"
	Else If v="4" Then
		Return "JM"
	Else If v="5" Then
		Return "<*"
	Else If v="7" Then
		Return "qO"
	Else If v="8" Then
		Return "[/"
	Else If v="9" Then
		Return "D>"
	Else If v="a" Then
		Return "JF"
	Else If v="b" Then
		Return "y/"
	Else If v="c" Then
		Return "7Q"
	Else If v="d" Then
		Return "TS"
	Else If v="e" Then
		Return "zB"
	Else If v="f" Then
		Return "9c"
	Else If v="g" Then
		Return "Ig"
	Else If v="h" Then
		Return "lt"
	Else If v="i" Then
		Return "Yl"
	Else If v="j" Then
		Return "&+"
	Else If v="k" Then
		Return "DT"
	Else If v="l" Then
		Return "-s"
	Else If v="m" Then
		Return "MH"
	Else If v="n" Then
		Return "aC"
	Else If v="o" Then
		Return "8n"
	Else If v="p" Then
		Return "J/"
	Else If v="q" Then
		Return "a%"
	Else If v="r" Then
		Return "J{"
	Else If v="s" Then
		Return ")n"
	Else If v="t" Then
		Return "_Y"
	Else If v="u" Then
		Return "V7"
	Else If v="v" Then
		Return "[f"
	Else If v="w" Then
		Return "2L"
	Else If v="x" Then
		Return "FM"
	Else If v="y" Then
		Return "{x"
	Else If v="z" Then
		Return "_d"
	Else If v="A" Then
		Return "Of"
	Else If v="B" Then
		Return "P["
	Else If v="C" Then
		Return "b."
	Else If v="D" Then
		Return "L1"
	Else If v="E" Then
		Return "LZ"
	Else If v="F" Then
		Return "GE"
	Else If v="G" Then
		Return "rj"
	Else If v="H" Then
		Return "gG"
	Else If v="I" Then
		Return "3U"
	Else If v="J" Then
		Return "aN"
	Else If v="K" Then
		Return "+&"
	Else If v="L" Then
		Return "7m"
	Else If v="M" Then
		Return "o4"
	Else If v="N" Then
		Return "D]"
	Else If v="O" Then
		Return "pG"
	Else If v="P" Then
		Return "2>"
	Else If v="Q" Then
		Return ")D"
	Else If v="R" Then
		Return "zJ"
	Else If v="S" Then
		Return "+x"
	Else If v="T" Then
		Return "0L"
	Else If v="U" Then
		Return "+6"
	Else If v="V" Then
		Return "bN"
	Else If v="W" Then
		Return ".E"
	Else If v="X" Then
		Return "oi"
	Else If v="Y" Then
		Return "b="
	Else If v="Z" Then
		Return "-/"
	Else If v="~" Then
		Return "(6"
	Else If v="`" Then
		Return "xp"
	Else If v="!" Then
		Return "UN"
	Else If v="#" Then
		Return "ha"
	Else If v="$" Then
		Return "&F"
	Else If v="%" Then
		Return "[e"
	Else If v="^" Then
		Return "G9"
	Else If v="&" Then
		Return "yL"
	Else If v="*" Then
		Return "In"
	Else If v="(" Then
		Return "Ca"
	Else If v=")" Then
		Return "Xg"
	Else If v="_" Then
		Return "IP"
	Else If v="-" Then
		Return "lG"
	Else If v="=" Then
		Return "N!"
	Else If v="+" Then
		Return "OD"
	Else If v="/" Then
		Return "[c"
	Else If v="?" Then
		Return "!+"
	Else If v="<" Then
		Return "7s"
	Else If v=">" Then
		Return "m#"
	Else If v="." Then
		Return "=b"
	Else If v="{" Then
		Return "*]"
	Else If v="}" Then
		Return "wc"
	Else If v="[" Then
		Return "#G"
	Else If v="]" Then
		Return "0T"
	Else If v="," Then
		Return "<>"
	Else If v="'" Then
		Return "a*"
	Else If v="6" Then
		Return "rS"
	Else
		Return v & v
	End If
End Sub

Public Sub Encryption_stn1(t As String) As String

	Dim txt As String

	Dim tx1 As String = ""
	tx1 = ""
	Dim cec(t.Length) As String
	txt = t

	For i = 0 To t.Length - 1
		Dim x As String
		x = txt.Substring(txt.Length - 1)
		cec(i) = sina_ENC_stn1(x)
		txt = txt.SubString2(0, txt.Length - 1)
	Next
	tx1 = ""
	For j = 0 To cec.Length - 1
		tx1 = cec(j) & tx1

	Next

	'''''''''''''''''''''''''''''''''''''''''''''''''''''''
	Return tx1
End Sub



Public Sub sina_ENC_stn1(v As String) As String

	If v="0" Then
		Return "8TrD"
	Else If v="1" Then
		Return "dG3I"
	Else If v="2" Then
		Return "lKj^"
	Else If v="3" Then
		Return "U-Wx"
	Else If v="4" Then
		Return "cq`c"
	Else If v="5" Then
		Return "B&Up"
	Else If v="7" Then
		Return "3^n?"
	Else If v="8" Then
		Return "gT^2"
	Else If v="9" Then
		Return "Z+.c"
	Else If v="a" Then
		Return "p].D"
	Else If v="b" Then
		Return "<Jc)"
	Else If v="c" Then
		Return ",<e1"
	Else If v="d" Then
		Return "d)]t"
	Else If v="e" Then
		Return "Dnrw"
	Else If v="f" Then
		Return "64Sy"
	Else If v="g" Then
		Return "evOV"
	Else If v="h" Then
		Return "W3g&"
	Else If v="i" Then
		Return "KUBo"
	Else If v="j" Then
		Return "oF])"
	Else If v="k" Then
		Return "=jQ("
	Else If v="l" Then
		Return "1BN&"
	Else If v="m" Then
		Return "_/!$"
	Else If v="n" Then
		Return "BsH/"
	Else If v="o" Then
		Return "8T$4"
	Else If v="p" Then
		Return ",)2]"
	Else If v="q" Then
		Return "Jq,N"
	Else If v="r" Then
		Return "{$]T"
	Else If v="s" Then
		Return "kGhY"
	Else If v="t" Then
		Return "T?W)"
	Else If v="u" Then
		Return "}+=p"
	Else If v="v" Then
		Return "jEK2"
	Else If v="w" Then
		Return "gy**"
	Else If v="x" Then
		Return "K7fM"
	Else If v="y" Then
		Return "VN=%"
	Else If v="z" Then
		Return "RC4T"
	Else If v="A" Then
		Return "/dhN"
	Else If v="B" Then
		Return "MBI_"
	Else If v="C" Then
		Return "FkZz"
	Else If v="D" Then
		Return "_Wx."
	Else If v="E" Then
		Return "L`9s"
	Else If v="F" Then
		Return "x98&"
	Else If v="G" Then
		Return "%z/?"
	Else If v="H" Then
		Return "1glP"
	Else If v="I" Then
		Return "8OtO"
	Else If v="J" Then
		Return "B-D_"
	Else If v="K" Then
		Return ".3b!"
	Else If v="L" Then
		Return "e2[2"
	Else If v="M" Then
		Return "DtJ`"
	Else If v="N" Then
		Return "5t*5"
	Else If v="O" Then
		Return "UB^~"
	Else If v="P" Then
		Return "+yKp"
	Else If v="Q" Then
		Return "KUlK"
	Else If v="R" Then
		Return "~odq"
	Else If v="S" Then
		Return "#qi$"
	Else If v="T" Then
		Return "$3Qw"
	Else If v="U" Then
		Return "83x}"
	Else If v="V" Then
		Return "Inz."
	Else If v="W" Then
		Return "$IO{"
	Else If v="X" Then
		Return ",7&L"
	Else If v="Y" Then
		Return ",fH?"
	Else If v="Z" Then
		Return "tj1}"
	Else If v="~" Then
		Return "<^dt"
	Else If v="`" Then
		Return "Gmp`"
	Else If v="!" Then
		Return "D=jX"
	Else If v="#" Then
		Return "F`mE"
	Else If v="$" Then
		Return "W44?"
	Else If v="%" Then
		Return "<LG4"
	Else If v="^" Then
		Return "plD/"
	Else If v="&" Then
		Return "#uk{"
	Else If v="*" Then
		Return "L,n["
	Else If v="(" Then
		Return ">}(G"
	Else If v=")" Then
		Return "R}M6"
	Else If v="_" Then
		Return "_`mg"
	Else If v="-" Then
		Return ".%jm"
	Else If v="=" Then
		Return "iAs}"
	Else If v="+" Then
		Return "bGqV"
	Else If v="/" Then
		Return "[,f["
	Else If v="?" Then
		Return "{`6T"
	Else If v="<" Then
		Return "sXCJ"
	Else If v=">" Then
		Return "l83="
	Else If v="." Then
		Return "%N6I"
	Else If v="{" Then
		Return "IS-4"
	Else If v="}" Then
		Return "bV?P"
	Else If v="[" Then
		Return "2Mw}"
	Else If v="]" Then
		Return "i%Oz"
	Else If v="," Then
		Return "J+,7"
	Else If v="'" Then
		Return "1BB^"
	Else If v="6" Then
		Return "r,W5"
	Else
		Return v & v & v & v
	End If
End Sub

Sub Create_HardLicence(Rand_Number As String,P_Serial) As String

	Dim Hardware_licence As String

	Dim ph As Phone
	Dim bs As Base64
	Hardware_licence=Rand_Number&ph.Product&ph.Manufacturer&ph.Model&ph.GetSettings("android_id")
	Hardware_licence=bs.EncodeBtoS(Hardware_licence.GetBytes("UTF8"),0,Hardware_licence.GetBytes("UTF8").Length)
	Hardware_licence=Hardware_licence&P_Serial

	Hardware_licence= Encryption_stn1(Hardware_licence)

	Hardware_licence= Hardware_licence.SubString2(Hardware_licence.Length-4,Hardware_licence.Length)&Hardware_licence.SubString2(0,Hardware_licence.Length-4)

	Return Interchange_stn.Encryption_Query_String_stn(Hardware_licence)

End Sub