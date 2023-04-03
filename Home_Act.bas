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

End Sub

Sub Globals

Dim sld1 As SlidingMenu


	Private lbl_Strip As Label
	Private lbl_Toolbar As Label
	''''''''''''''''''''''''''''''''''''''''''''''''
	Private btn_Definition_Permissions As Button
	Private btn_Help As Button
	Private btn_AboutWe As Button
	Private btn_Contact As Button
	Private btn_Advice As Button
	Private btn_Exit As Button
	
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
	Private img_Border_Slide5 As ImageView
	Private img_Border_Slide6 As ImageView
	Private img_Border_Slide4 As ImageView
	Private img_Border_Slide3 As ImageView
	Private img_Border_Slide2 As ImageView
	Private img_Border_Slide1 As ImageView
	Private main_panel As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
Activity.LoadLayout("Home_Screen")
main_panel.LoadLayout("loyout_items")

main_panel.SetVisibleAnimated(0,False)
main_panel.SetVisibleAnimated(3000,True)

sizeviewV3.SetPX(Activity.Width,Activity.Height,320,480)
sizeviewV3.SetPastScreenSizetoInche(4.444,6.667)


sizeviewV3.SetSize_lbl_Views(lbl_Strip,10,33,50,254,25/1.5773684210526315789473684210526)
sizeviewV3.SetSize_lbl_Views(lbl_Toolbar,20,288,30,30,0)
sizeviewV3.SetSizeViews(main_panel,110,10,370,300,0)

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
sizeviewV3.SetSize_btn(btn_Definition_Permissions,10,40,39,225,25/1.5773684210526315789473684210526)
sizeviewV3.SetSize_btn(btn_Help,69,40,39,225,25/1.5773684210526315789473684210526)
sizeviewV3.SetSize_btn(btn_AboutWe,128,40,39,225,25/1.5773684210526315789473684210526)
sizeviewV3.SetSize_btn(btn_Contact,187,40,39,225,25/1.5773684210526315789473684210526)
sizeviewV3.SetSize_btn(btn_Advice,246,40,39,225,25/1.5773684210526315789473684210526)
sizeviewV3.SetSize_btn(btn_Exit,305,40,39,225,25/1.5773684210526315789473684210526)

main_panel.RequestFocus

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
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub lbl_Toolbar_Click
sld1.ShowMenu
End Sub


Sub btn_Definition_Permissions_Click
	Definition_Permissions_ACT.Current_Tab=1
	StartActivity(Definition_Permissions_ACT)
	Activity.Finish()	
End Sub

Sub btn_Help_Click
	StartActivity(Help_ACT)
	Activity.Finish()
End Sub

Sub btn_AboutWe_Click
	Dim U_In As Intent
	U_In.Initialize(U_In.ACTION_VIEW,"http://modernplus.ir/about.html")
	StartActivity(U_In)
End Sub

Sub btn_Contact_Click
	StartActivity(Contact_ACT)
	Activity.Finish
End Sub

Sub btn_Advice_Click
	StartActivity(Send_Comment_ACT)
	Activity.Finish
End Sub

Sub btn_Exit_Click
		If Msgbox2("میخواهید از برنامه خارج شوید ؟","","بله","","نه",Null)=DialogResponse.POSITIVE Then
		ExitApplication()
		End If
End Sub



Sub btn_Home_Slidebar_Click
sld1.HideMenus
End Sub

Sub btn_LastDefinitionAccessibility_SlideBar_Click
	Definition_Permissions_ACT.Current_Tab=0
	StartActivity(Definition_Permissions_ACT)
	Activity.Finish()
End Sub

Sub btn_Help_SlideBAr_Click
StartActivity(Help_ACT)
Activity.Finish()
End Sub

Sub btn_Definition_SlideMenu_Click
	Definition_Permissions_ACT.Current_Tab=1
	StartActivity(Definition_Permissions_ACT)
	Activity.Finish()
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
	btn_Exit_Click
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		
		btn_Exit_Click
		
	End If
	Return True
	
End Sub