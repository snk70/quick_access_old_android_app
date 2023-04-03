Type=Activity
Version=6.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
Dim Current_Tab As Int

End Sub

Sub Globals
Dim sc As Security_Class_List
Dim List_URL,List_Fname,List_Lname As List
Dim sld1 As SlidingMenu

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	Private img_SlideMenu As ImageView
	Private img_HomeScreen As ImageView
	Private btn_ChooseDirectory As Button
	Private btn_InstalledApps As Button
	Private btn_SettingPArts As Button
	Private btn_ContactChoose As Button
	Private Label1 As Label
	'''''''''''''''''''''''''''''''
	Private btn_RemovePermissions As Button
	Private btn_Home_Slidebar As Button
	Private img_Home_SlideBar As ImageView
	Private btn_LastDefinitionAccessibility_SlideBar As Button
	Private img_LastDefinitionAccessibility_SlideBar As ImageView
	Private btn_Help_SlideBAr As Button
	Private img_Help_SlideBAr As ImageView
	Private btn_Definition_SlideMenu As Button
	Private img_Definition_SlideMenu As ImageView
	Private btn_Contact_SlideMenu As Button
	Private img_Contact_SlideMenu As ImageView
	Private btn_Comments_SlideMenu As Button
	Private img_Comments_SlideMenu As ImageView
	Private btn_Exit_SlideMenu As Button
	Private img_Exit_SlideMenu As ImageView
	Private TabHost1 As TabHost


	Private ListView1 As ListView
	Private EditText1 As EditText
	
	
	Private img_Border_Slide1 As ImageView
	Private img_Border_Slide2 As ImageView
	Private img_Border_Slide3 As ImageView
	Private img_Border_Slide4 As ImageView
	Private img_Border_Slide5 As ImageView
	Private img_Border_Slide6 As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)

If Rnd(0,10)=0 Then
	StartService(check_servis)
End If

If check_servis.OK_Status=False Then
	ExitApplication
End If

	Activity.LoadLayout("Permissions_ACT")

	ProgressDialogShow("لطفا کمی صبر کنید...")

	TabHost1.AddTab("دسترسی های تعیین شده","LastPermission_Definition")

	TabHost1.AddTab("تعیین دسترسی ها","DefinitionandRemove_Permission")


	sizeviewV3.SetPX(Activity.Width-5%x,Activity.Height,320,480)
	sizeviewV3.SetPastScreenSizetoInche(4.444,6.667)


#Region دسترسی های اضافه شده
	ListView1.Top=(60/480)*Activity.Height
	ListView1.Height=(410/480)*Activity.Height
	ListView1.Width=Activity.Width
	sizeviewV3.SetSize_txt(EditText1,0,0,50,320,20)
#End Region



#Region Resize_Views

	sizeviewV3.SetSize_img(img_SlideMenu,5,280,30,30,0)
	sizeviewV3.SetSize_img(img_HomeScreen,5,10,30,30,0)
	sizeviewV3.SetSize_lbl_Views(Label1,44,10,65,300,16)
	sizeviewV3.SetSize_btn(btn_ChooseDirectory,114,10,50,130,14)
	sizeviewV3.SetSize_btn(btn_InstalledApps,114,180,50,130,14)
	sizeviewV3.SetSize_btn(btn_SettingPArts,253,180,50,130,14)
	sizeviewV3.SetSize_btn(btn_ContactChoose,253,10,50,130,14)
	sizeviewV3.SetSize_btn(btn_RemovePermissions,173,100,70,96,14)


#End Region

	sizeviewV3.SetPX(Activity.Width,Activity.Height,320,480)
#Region سلاید منو
	'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''Buttons
	sld1.Initialize("")
	sld1.Mode=sld1.RIGHT
	sld1.BehindOffset=(150/320)*Activity.Width
	sld1.Menu.LoadLayout("SlideMenu_Loyout")
		
	sizeviewV3.SetSize_btn(btn_Home_Slidebar,0,0,38,171,20/1.5773684210526315789473684210526)
	sizeviewV3.SetSize_btn(btn_LastDefinitionAccessibility_SlideBar,38,0,38,171,20/1.5773684210526315789473684210526)
	sizeviewV3.SetSize_btn(btn_Help_SlideBAr,76,0,38,171,20/1.5773684210526315789473684210526)
	sizeviewV3.SetSize_btn(btn_Definition_SlideMenu,114,0,38,171,20/1.5773684210526315789473684210526)
	sizeviewV3.SetSize_btn(btn_Contact_SlideMenu,151,0,38,171,20/1.5773684210526315789473684210526)
	sizeviewV3.SetSize_btn(btn_Comments_SlideMenu,188,0,38,171,20/1.5773684210526315789473684210526)
	sizeviewV3.SetSize_btn(btn_Exit_SlideMenu,226,0,38,171,20)
	''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''ImageViews
	sizeviewV3.SetSize_img(img_Home_SlideBar,2,4,34,36,0)
	sizeviewV3.SetSize_img(img_LastDefinitionAccessibility_SlideBar,40,4,34,36,0)
	sizeviewV3.SetSize_img(img_Help_SlideBAr,78,4,34,36,0)
	sizeviewV3.SetSize_img(img_Definition_SlideMenu,116,4,34,36,0)
	sizeviewV3.SetSize_img(img_Contact_SlideMenu,153,4,34,36,0)
	sizeviewV3.SetSize_img(img_Comments_SlideMenu,190,4,34,36,0)
	sizeviewV3.SetSize_img(img_Exit_SlideMenu,226,4,34,36,0)

#Region Borders

	sizeviewV3.SetSize_img(img_Border_Slide1,38,0,1,171,0)
	sizeviewV3.SetSize_img(img_Border_Slide2,76,0,1,171,0)
	sizeviewV3.SetSize_img(img_Border_Slide3,114,0,1,171,0)
	sizeviewV3.SetSize_img(img_Border_Slide4,151,0,1,171,0)
	sizeviewV3.SetSize_img(img_Border_Slide5,188,0,1,171,0)
	sizeviewV3.SetSize_img(img_Border_Slide6,226,0,1,171,0)

	img_Border_Slide1.Color=Colors.Red
	img_Border_Slide2.Color=Colors.Red
	img_Border_Slide3.Color=Colors.Red
	img_Border_Slide4.Color=Colors.Red
	img_Border_Slide5.Color=Colors.Red
	img_Border_Slide6.Color=Colors.Red



#End Region







#End Region


	TabHost1.CurrentTab=Current_Tab


	
	'"content://com.android.contacts/contacts/1000"


	Dim p1 As Phone
	p1.SetScreenOrientation(-1)

	Filling_ListView'پر کردن لیست ویو


	If Installed_Applications.Intent_AddedToList_InstallApp=True Then
																				
																				
																				
		List_URL.Add(Installed_Applications.IntentApp_URI)
		List_Fname.Add(Installed_Applications.IntentApp_FN)
		List_Lname.Add("Application : "&Installed_Applications.IntentApp_LN)
																				
		ToastMessageShow("برنامه "&Installed_Applications.IntentApp_FN&" اضافه شد",True)
		Save_Lists
																				
		Filling_ListView
																				
		Installed_Applications.Intent_AddedToList_InstallApp=False
		Installed_Applications.IntentApp_URI=""
		Installed_Applications.IntentApp_FN=""
		Installed_Applications.IntentApp_LN=""
		Installed_Applications.Intent_AddedToList_InstallApp=False

	Else If Setting_Parts.Intent_Added=True Then
																					
																					
		List_URL.Add(Setting_Parts.Intent_URL)
																					
		List_Fname.Add(Setting_Parts.Intent_FN)
																					
		List_Lname.Add(Setting_Parts.Intent_LN)
		
		Setting_Parts.Intent_Added=False
																
		Save_Lists
		Filling_ListView
																					
		ToastMessageShow(" تنظیمات "&Setting_Parts.Intent_FN&" اضافه شد",True)
																					
																					
		Setting_Parts.Intent_Added=False
		Setting_Parts.Intent_FN=""
		Setting_Parts.Intent_LN=""
		Setting_Parts.Intent_URL=""
	
	Else If Selected_Contact.contact_Added=True Then
		
		
		List_URL.Add(Selected_Contact.Value_Intent)
		
		List_Fname.Add(Selected_Contact.F_Intent)
												
		List_Lname.Add(Selected_Contact.L_Intent)
		
		Selected_Contact.contact_Added=False
		
		ToastMessageShow("مخاطب "&Selected_Contact.F_Intent&" اضافه شد",True)
		
		Save_Lists
		Filling_ListView
		
	End If



	ProgressDialogHide




End Sub

Sub Activity_Resume
Filling_ListView
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Filling_ListView
	Reload_Lists
	ListView1.Clear
	For k=0 To List_URL.Size-1
		ListView1.AddTwoLines2("             "&List_Fname.Get(List_URL.Size-1-k),"    "&List_Lname.Get(List_URL.Size-1-k),List_URL.Get(List_URL.Size-1-k))
	Next
	ListView1.AddTwoLinesAndBitmap("","",Null)
End Sub

Sub Reload_Lists
List_Fname.Initialize
List_Lname.Initialize
List_URL.Initialize
	If File.Exists(File.DirInternal,"fsaccs/sqrgdb_lsit_Fani_t.txt")=True And File.Exists(File.DirInternal,"fsaccs/sqrgdb_lsit_Landy_C.txt")=True And File.Exists(File.DirInternal,"fsaccs/sqrgdb_Lostictevision_URL.txt")=True Then
	
	Dim List_Fname_DEC As List
	List_Fname_DEC.Initialize
	List_Fname_DEC=File.ReadList(File.DirInternal,"fsaccs/sqrgdb_lsit_Fani_t.txt")
	'''''''''''''''''''''''''''''''''''
	Dim List_Lname_DEC As List
	List_Lname_DEC.Initialize
	List_Lname_DEC=File.ReadList(File.DirInternal,"fsaccs/sqrgdb_lsit_Landy_C.txt")
	'''''''''''''''''''''''''''''''''''
	Dim List_URL_DEC As List
	List_URL_DEC.Initialize
	List_URL_DEC=File.ReadList(File.DirInternal,"fsaccs/sqrgdb_Lostictevision_URL.txt")
	''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	List_Fname=sc.List_Dec(List_Fname_DEC)
	List_Lname=sc.List_Dec(List_Lname_DEC)
	List_URL=sc.List_Dec(List_URL_DEC)
End If
	
End Sub


Sub SetNumber_GetID(Numb As String) As String
	
	Dim cnts As Contacts2
	Dim lst As List
	lst.Initialize
	lst= cnts.GetAll(False,False)
	
	For i=0 To lst.Size-1
		Dim cnt As Contact
		cnt=lst.Get(i)
		Dim map As Map
		map.Initialize
		map=cnt.GetPhones
		#Region جدا سازی و تجزیه و تحلیل
		Dim string1,string2 As String
		string1=map
		Try
		string2=string1.SubString2(9,string1.IndexOf("=2"))'اولین شماره ذخیره شده برای مخاطب در متغیر String2 ذحیره میشود
		If " "&string2=Numb Then
			Return cnt.Id
			Return
		End If
		Catch
			
		End Try
		#End Region
		
	Next
End Sub



Sub Save_Lists
		
		
		Dim List_URL_ENC As List
		List_URL_ENC.Initialize
		List_URL_ENC=sc.List_Enc(List_URL)
		'''''''''''''''''''''''''
		Dim List_Lname_ENC As List
		List_Lname_ENC.Initialize
		List_Lname_ENC=sc.List_Enc(List_Lname)
		'''''''''''''''''''''''''''''''''''''''
		Dim List_Fname_ENC As List
		List_Fname_ENC.Initialize
		List_Fname_ENC=sc.List_Enc(List_Fname)
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
		File.WriteList(File.DirInternal,"fsaccs/sqrgdb_lsit_Fani_t.txt",List_Fname_ENC)
		File.WriteList(File.DirInternal,"fsaccs/sqrgdb_lsit_Landy_C.txt",List_Lname_ENC)
		File.WriteList(File.DirInternal,"fsaccs/sqrgdb_Lostictevision_URL.txt",List_URL_ENC)
End Sub

Sub img_SlideMenu_Click
sld1.ShowMenu()
End Sub

Sub img_HomeScreen_Click
	StartActivity(Home_Act)
	Activity.Finish()
End Sub

Sub btn_ContactChoose_Click
ProgressDialogShow2("لطفا کمی صبر کنید",False)
	StartActivity(Selected_Contact)
	Activity.Finish()
End Sub



Sub btn_ChooseDirectory_Click
	
	Dim Location_Dialog As FileDialog
	Location_Dialog.FilePath=File.DirRootExternal
	Dim Location_Dialog_Response As Int=-5
	'	Location_Dialog_Response =	Location_Dialog.Show("انتخاب مسیر فایل یا پوشه","انتخاب پوشه","انتخاب فایل","لغو",Null)
	Location_Dialog_Response=Location_Dialog.Show("انتخاب مسیر فایل یا پوشه","انتخاب پوشه","لغو","انتخاب فایل",Null)
	
	'	Msgbox(Location_Dialog_Response&CRLF&DialogResponse.POSITIVE&CRLF&DialogResponse.CANCEL&CRLF&DialogResponse.NEGATIVE,"")
	
	
	If Location_Dialog_Response<>DialogResponse.NEGATIVE And Location_Dialog_Response<>DialogResponse.POSITIVE Then
		Return
	End If
								
							
								
	Dim msgdialog As InputDialog
	msgdialog.Show("","لطفا نام دلخواهی برای دسترسی مورد نظرتان وارد کنید","تأیید","لغو","",Null)
				
	If msgdialog.Response<>DialogResponse.POSITIVE Then
		Return
	End If
				
	If Location_Dialog_Response=DialogResponse.POSITIVE Then
		'Select Folder

		List_Fname.Add(msgdialog.Input)
		List_Lname.Add(Location_Dialog.FilePath)
		List_URL.Add(Location_Dialog.FilePath)
		ToastMessageShow("مسیر "&msgdialog.Input&" اضافه شد",True)
		
	Else If Location_Dialog_Response=DialogResponse.NEGATIVE Then
		'Select File
		If Location_Dialog.ChosenName="" Then
			List_Fname.Add(msgdialog.Input)
			List_Lname.Add(Location_Dialog.FilePath)
			List_URL.Add(Location_Dialog.FilePath)
			ToastMessageShow("مسیر "&Location_Dialog.FilePath&" اضافه شد",True)
		Else
			List_Fname.Add(msgdialog.Input)
			List_Lname.Add("Path : "&Location_Dialog.FilePath&"/"&Location_Dialog.ChosenName)
			List_URL.Add(Location_Dialog.FilePath&"/"&Location_Dialog.ChosenName)
			ToastMessageShow("مسیر "&Location_Dialog.FilePath&"/"&Location_Dialog.ChosenName&" اضافه شد",True)
		End If
	End If
	Save_Lists
	Filling_ListView

End Sub

Sub btn_InstalledApps_Click
	ProgressDialogShow2("لطفا کمی صبر کنید ...",False)
	StartActivity(Installed_Applications)
	Activity.Finish()
	
	
End Sub

Sub btn_SettingPArts_Click
	StartActivity(Setting_Parts)
	Activity.Finish()	
End Sub

Sub btn_RemovePermissions_Click
	
	Dim int1 As Int=InputList(List_Fname,"دسترسی مورد نظر را حذف نمائید",-1)
	If int1<0 Then
	Return		
	End If

	Dim str As String=List_Fname.Get(int1)
	
	If Msgbox2("میخواهید دسترسی "&str&" را حذف کنید ؟","","بله","","نه",Null)=DialogResponse.POSITIVE Then
		
	List_URL.RemoveAt(int1)
	
	List_Fname.RemoveAt(int1)
	
	List_Lname.RemoveAt(int1)
	
	Save_Lists
	ToastMessageShow("دسترسی "&str&" حذف شد",True)
	
	Filling_ListView

		
	End If
	

	
		
End Sub


Sub ListView1_ItemClick (Position As Int, Value As Object)
'	Dim ListView_Intents As Intent
'	ListView_Intents.Initialize(ListView_Intents.ACTION_VIEW,Value)
'	StartActivity(ListView_Intents)
If Value<>"" Then

				Dim str As String=Value
				
				If str.StartsWith("content://com.android.contacts/contacts/")=True Then
						Dim ListView_Intents As Intent
						ListView_Intents.Initialize(ListView_Intents.ACTION_VIEW,Value)
						StartActivity(ListView_Intents)
								
					Else If str.StartsWith("android.settings.")=True Then
					Dim i As Intent
					i.Initialize(Value, "")
					StartActivity(i)
					
					Else
				
					Try	
							
							Dim pg As PackageManager
							StartActivity(pg.GetApplicationIntent(Value))
							Activity.Finish()
					
					Catch
					
						If File.IsDirectory("",Value)=True Then
						File_Manager.Get_Path=Value
						StartActivity(File_Manager)
						Activity.Finish()
				
						Else
						OpenFileOrFolder(Value)
						End If
					
					
					
					End Try
					
					
				End If
				
				
End If




End Sub


Sub OpenFileOrFolder(File_Path As String)
	
	
	Dim File_Selected As AriaFile
	File_Selected.initialize2(File_Path)
	Dim FileUtils As AriaFileUtils
	
	
	If File_Selected.IsDirectory Then
		Else
			
			'age item file bud pas az tariqe methode zir un ro ejra mikonim
			Try
				'in method GetIntentForOpenFile -> intent ejra va ya edit file o be ma mide baraye ejra
				StartActivity(FileUtils.GetIntentForOpenFile(File_Selected,True))'true for view
			Catch
				'age barnamei emkane namayesh file ro nadasht error mide pas inja error ronamayesh midim
				ToastMessageShow("there is no app to handle this file",False)
			End Try
			
		
	End If

End Sub



Sub btn_Home_Slidebar_Click
	StartActivity(Home_Act)
	Activity.Finish()
End Sub

Sub btn_LastDefinitionAccessibility_SlideBar_Click
	TabHost1.CurrentTab=0
	sld1.HideMenus
End Sub

Sub btn_Help_SlideBAr_Click
	StartActivity(Help_ACT)
	Activity.Finish()
End Sub

Sub btn_Definition_SlideMenu_Click
	sld1.HideMenus
End Sub

Sub btn_Contact_SlideMenu_Click
	StartActivity(Contact_ACT)
	Activity.Finish()
End Sub

Sub btn_Comments_SlideMenu_Click
	StartActivity(Send_Comment_ACT)
	Activity.Finish()
End Sub

Sub btn_Exit_SlideMenu_Click
		If Msgbox2("میخواهید از برنامه خارج شوید ؟","","بله","","نه",Null)=DialogResponse.POSITIVE Then
		ExitApplication()
		End If
End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		
		StartActivity(Home_Act)
		Activity.Finish()
		
	End If
	
	Return True
End Sub


Sub EditText1_TextChanged (Old As String, New As String)
ListView1.Clear
Dim lst As List
lst.Initialize
lst=SearchModule.Searching_NumberResult(List_Fname,New)

	For i=0 To lst.Size-1
		
		Dim nm As Int=lst.Get(i)
		
		ListView1.AddTwoLines2("             "&List_Fname.Get(nm),"	"&List_Lname.Get(nm),List_URL.Get(nm))
		
			
	Next


	
End Sub