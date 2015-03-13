unit uMain;

interface

uses
  System.SysUtils, System.Types, System.UITypes, System.Classes, System.Variants,
  FMX.Types, FMX.Controls, FMX.Forms, FMX.Dialogs, System.Sensors, FMX.StdCtrls,
  FMX.Edit, FMX.WebBrowser, FMX.ListBox, FMX.Layouts, System.Sensors.Components,
  FMX.Controls.Presentation, FMX.EditBox, FMX.NumberBox;

type
  TLocationForm = class(TForm)
    LocationSensor1: TLocationSensor;
    WebBrowser1: TWebBrowser;
    ListBox1: TListBox;
    lbLatitude: TListBoxItem;
    lbLongitude: TListBoxItem;
    ToolBar1: TToolBar;
    Label1: TLabel;
    Button1: TButton;
    procedure LocationSensor1LocationChanged(Sender: TObject; const OldLocation,
      NewLocation: TLocationCoord2D);
    procedure FormCreate(Sender: TObject);
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  LocationForm: TLocationForm;

implementation

{$R *.fmx}
{$R *.LgXhdpiPh.fmx ANDROID}
{$R *.iPad.fmx IOS}
{$R *.iPhone.fmx IOS}
{$R *.iPhone4in.fmx IOS}

procedure TLocationForm.Button1Click(Sender: TObject);
begin
     WebBrowser1.Reload();
end;

procedure TLocationForm.FormCreate(Sender: TObject);
begin
LocationSensor1.Active:=true;
end;

procedure TLocationForm.LocationSensor1LocationChanged(Sender: TObject;
  const OldLocation, NewLocation: TLocationCoord2D);
const

  OurMapsURL: String = 'watchdogskrajina.herokuapp.com/api/%s/%s/%s';

var
  _slash,_id, ENUSLat, ENUSLong: String; // holders for URL strings
begin
  _id:='55034a8301c3da49171774c6';
  ENUSLat := NewLocation.Latitude.ToString(ffGeneral, 5, 2, TFormatSettings.Create('en-US'));
  ENUSLong := NewLocation.Longitude.ToString(ffGeneral, 5, 2, TFormatSettings.Create('en-US'));
  { konverzija lokacije }
  lbLatitude.Text := 'Latitude: ' + ENUSLat;
  lbLongitude.Text := 'Longitude: ' + ENUSLong;
  _slash:='/';

  {dodaj lokaciju i prati je na mapi }
  WebBrowser1.Navigate(Format(OurMapsURL, [_id,ENUSLat, ENUSLong]));
  WebBrowser1.EvaluateJavaScript('Alert()');
end;

end.
