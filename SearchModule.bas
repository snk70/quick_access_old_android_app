Type=StaticCode
Version=6.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals


End Sub



Sub Searching_ListResult(lists As List,Text As String) As List
Dim lst As List
lst.Initialize


				For i=0 To lists.Size-1

				Dim str1 As String=lists.Get(i)
					If str1.IndexOf(Text)<>-1 Then
					lst.Add(str1)
					End If
				Next

Return lst

End Sub



Sub Searching_NumberResult(lists As List,Text As String) As List
Dim lst As List
lst.Initialize


				For i=0 To lists.Size-1

				Dim str1 As String=lists.Get(i)
					If str1.IndexOf(Text)<>-1 Then
					lst.Add(i)
					End If
				Next

Return lst


End Sub