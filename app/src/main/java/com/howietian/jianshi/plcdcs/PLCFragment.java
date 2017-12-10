package com.howietian.jianshi.plcdcs;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.howietian.jianshi.MainActivity;
import com.howietian.jianshi.R;
import com.howietian.jianshi.adapters.MyListAdapter;
import com.howietian.jianshi.constants.FlawDb;
import com.howietian.jianshi.db.DatabaseHelper;
import com.howietian.jianshi.entities.Flaw;
import com.howietian.jianshi.entities.PLC;
import com.howietian.jianshi.entities.Unit;
import com.howietian.jianshi.scada.InfoFragment;
import com.howietian.jianshi.scada.NetInfoFragment;
import com.howietian.jianshi.scada.SOtherFragment;
import com.howietian.jianshi.scada.ServerFragment;
import com.howietian.jianshi.scada.SoftwareFragment;
import com.howietian.jianshi.scada.SwitchFragment;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class PLCFragment extends Fragment {

  Toolbar toolbar;
  ListView listView;

  private static final int REQUEST_STORAGE = 1;
  /**
   * 第一行的内边距
   */
  private static final int CELL_PADDING_1 = 10;
  /**
   * 第二行的内边距
   */
  private static final int CELL_PADDING_2 = 15;
  /**
   * 第三行的内边距
   */
  private static final int CELL_PADDING_3 = 8;
  /**
   * 第四行的内边距
   */
  private static final int CELL_PADDING_8 = 8;
  /**
   * 第八行的内边距
   */

  private static final int CELL_HEIGHT = 80;
  private static BaseFont bfChinese;

  public static final int SCADA_SERVER = 0;

  static {
    try {
      bfChinese = BaseFont.createFont("STSongStd-Light",
              "UniGB-UCS2-H", false);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 默认字体
   */
  private static Font fontChinese_default = new Font(bfChinese, 12);
  /**
   * 表格第一行字体
   */
  private static Font fontChinese_1 = new Font(bfChinese, 17, Font.BOLD);
  /**
   * 表格第三行字体
   */
  private static Font fontChinese_3 = new Font(bfChinese, 12, Font.BOLD);

  private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

  private PInfoFragment pInfoFragment;
  private HostComputerFragment hostComputerFragment;
  private SlaveComputerFragment slaveComputerFragment;
  private PServerFragment pServerFragment;
  private PSwitchFragment pSwitchFragment;
  private PNetInfoFragment pNetInfoFragment;
  private POtherFragment pOtherFragment;

  private MyListAdapter adapter;
  private String[] titles = { "基本信息", "上位机", "下位机", "服务器", "交换机", "联网情况", "其他" };

  PLC plc = null;
  Unit unit = null;
  MainActivity activity;
  private Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      if(msg.what==0){
        showToast("导出报表成功！");
      }
    }
  };

  int count = 0;

  public PLCFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    activity = ((MainActivity) context);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View mainView = inflater.inflate(R.layout.fragment_plc, container, false);

    toolbar = (Toolbar) mainView.findViewById(R.id.toolbar);

    listView = (ListView) mainView.findViewById(R.id.lv_plc);
    adapter = new MyListAdapter(getContext(), titles);
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        setPages(i);
        //与item的背景变化有关
        adapter.setPosition(i);
        adapter.notifyDataSetChanged();
      }
    });

    setPages(0);

    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    // 设置回调fragment中的 onCreateOptionMenu(),必须要加
    setHasOptionsMenu(true);

    return mainView;
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    //清除 activity 的 toolbar 上的 menu
    menu.clear();
    inflater.inflate(R.menu.menu_save, menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_save:
        if (pInfoFragment == null) {
          showToast("请完善基本信息");
          return false;
        } else if (hostComputerFragment == null) {
          showToast("请完善上位机信息");
          return false;
        } else if (slaveComputerFragment == null) {
          showToast("请完善下位机信息");
          return false;
        } else if (pServerFragment == null) {
          showToast("请完善服务器信息");
          return false;
        } else if (pSwitchFragment == null) {
          showToast("请完善交换机信息");
          return false;
        } else if (pNetInfoFragment == null) {
          showToast("请完善联网信息");
          return false;
        } else if (pOtherFragment == null) {
          showToast("请完善其他新信息");
          return false;
         } else {
          plc = new PLC();
          unit = new Gson().fromJson(activity.getData(),Unit.class);
          PLC.Person person = new Gson().fromJson(pInfoFragment.getData(),PLC.Person.class);
          PLC.UpMachine upMachine = new Gson().fromJson(hostComputerFragment.getData(),PLC.UpMachine.class);
          PLC.DownMachine downMachine = new Gson().fromJson(slaveComputerFragment.getData(),PLC.DownMachine.class);
          PLC.Server server = new Gson().fromJson(pServerFragment.getData(),PLC.Server.class);
          PLC.Switch mSwitch = new Gson().fromJson(pSwitchFragment.getData(),PLC.Switch.class);
          PLC.NetInfo netInfo = new Gson().fromJson(pNetInfoFragment.getData(),PLC.NetInfo.class);
          PLC.Other other = new Gson().fromJson(pOtherFragment.getData(),PLC.Other.class);

          plc.setUid(unit.getId());
          plc.setPerson(person);
          plc.setUpMachine(upMachine);
          plc.setDownMachine(downMachine);
          plc.setServer(server);
          plc.setaSwitch(mSwitch);
          plc.setNetInfo(netInfo);
          plc.setOther(other);


          ContentValues cv = plc2ContentValues(plc);
          DatabaseHelper helper = new DatabaseHelper(getContext());
          SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
          sqLiteDatabase.insert(DatabaseHelper.TABLE_PLC,null,cv);

          final ProgressDialog dialog = new ProgressDialog(getContext());
          dialog.setMessage("正在导出报表...");
          dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
          dialog.show();

          new Thread(new Runnable() {
            @Override
            public void run() {
              while (count<=100){
                dialog.setProgress(count++);
                try {
                  Thread.sleep(20);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
              dialog.dismiss();
              Message message = new Message();
              message.what = 0;
              handler.sendMessage(message);

            }
          }).start();

          requestExportPdf();
        }

        break;
      case R.id.menu_camera:
        IntentIntegrator.forSupportFragment(this).initiateScan();
        IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("扫描二维码");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        break;
    }
    return true;
  }

  // Get the results:
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    if(result != null) {
      if(result.getContents() == null) {
        Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
      } else {
        //Toast.makeText(getContext(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

        pServerFragment.setServerInfo(result.getContents());
        showToast("服务器信息扫描成功！");


      }
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  /**
   * 申请导出PDF表格
   */
  private void requestExportPdf() {

    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
      ActivityCompat.requestPermissions(getActivity(),
              new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
              REQUEST_STORAGE);
    } else {
      //有权限，直接导出Pdf
      exportPdf();
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == REQUEST_STORAGE) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        //申请成功，可以导出Excel
        exportPdf();
      } else {
        Log.e("HHH", "申请权限失败");
      }
      return;
    }
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  public void exportPdf() {

    Document document = new Document(PageSize.LETTER, 36.0f, 36.0f, 36.0f, 36.0f);

    try {
      PdfWriter.getInstance(document, new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/鉴势 工控系统信息核查报告.pdf")));
      document.open();
      PdfPTable table = new PdfPTable(1);
      table.setSplitLate(false);
      table.setSplitRows(true);
      table.setWidthPercentage(100);
      int totalRowNum = 13;
      for (int i = 1; i <= totalRowNum; i++) {
        table.addCell(getTable(i));
      }
      document.add(table);
      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void showToast(String s) {
    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
  }

  // 设置fragment
  private void setPages(int position) {
    FragmentManager fm = getChildFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    hidePages(ft);
    switch (position) {
      case 0:
        if (pInfoFragment == null) {
          pInfoFragment = new PInfoFragment();
          ft.add(R.id.frameLayout, pInfoFragment);
        }
        ft.show(pInfoFragment);
        break;
      case 1:
        if (hostComputerFragment == null) {
          hostComputerFragment = new HostComputerFragment();
          ft.add(R.id.frameLayout, hostComputerFragment);
        }
        ft.show(hostComputerFragment);
        break;
      case 2:
        if (slaveComputerFragment == null) {
          slaveComputerFragment = new SlaveComputerFragment();
          ft.add(R.id.frameLayout, slaveComputerFragment);
        }
        ft.show(slaveComputerFragment);
        break;
      case 3:
        if (pServerFragment == null) {
          pServerFragment = new PServerFragment();
          ft.add(R.id.frameLayout, pServerFragment);
        }
        ft.show(pServerFragment);
        break;
      case 4:
        if (pSwitchFragment == null) {
          pSwitchFragment = new PSwitchFragment();
          ft.add(R.id.frameLayout, pSwitchFragment);
        }
        ft.show(pSwitchFragment);
        break;
      case 5:
        if (pNetInfoFragment == null) {
          pNetInfoFragment = new PNetInfoFragment();
          ft.add(R.id.frameLayout, pNetInfoFragment);
        }
        ft.show(pNetInfoFragment);
        break;
      case 6:
        if (pOtherFragment == null) {
          pOtherFragment = new POtherFragment();
          ft.add(R.id.frameLayout, pOtherFragment);
        }
        ft.show(pOtherFragment);
        break;
    }
    ft.commit();
  }

  //隐藏所有的fragment
  private void hidePages(FragmentTransaction ft) {
    if (pInfoFragment != null) {
      ft.hide(pInfoFragment);
    }
    if (hostComputerFragment != null) {
      ft.hide(hostComputerFragment);
    }
    if (slaveComputerFragment != null) {
      ft.hide(slaveComputerFragment);
    }
    if (pServerFragment != null) {
      ft.hide(pServerFragment);
    }
    if (pSwitchFragment != null) {
      ft.hide(pSwitchFragment);
    }
    if (pNetInfoFragment != null) {
      ft.hide(pNetInfoFragment);
    }
    if (pOtherFragment != null) {
      ft.hide(pOtherFragment);
    }
  }

  private ContentValues plc2ContentValues(PLC plc){
    ContentValues contentValues = new ContentValues();
    PLC.Person person = plc.getPerson();
    PLC.UpMachine upMachine = plc.getUpMachine();
    PLC.DownMachine downMachine = plc.getDownMachine();
    PLC.Server server = plc.getServer();
    PLC.Switch mSwitch = plc.getaSwitch();
    PLC.NetInfo netInfo = plc.getNetInfo();
    PLC.Other other = plc.getOther();

    contentValues.put("uid", plc.getUid());
    contentValues.put("sname", person.getSname());
    contentValues.put("sphone", person.getSphone());
    contentValues.put("sunit", person.getSunit());
    contentValues.put("sposition", person.getSposition());

    contentValues.put("upOsType",upMachine.getUpOsType());
    contentValues.put("upOsBrand",upMachine.getUpOsBrand());
    contentValues.put("upOsVersion",upMachine.getUpOsVersion());
    contentValues.put("upOsIsTrue",upMachine.getUpOsIsTrue());
    contentValues.put("upOsUpTime",upMachine.getUpOsUpTime());
    contentValues.put("upOsUpType",upMachine.getUpOsUpType());
    contentValues.put("upOsBrief",upMachine.getUpOsBrief());
    contentValues.put("upJCompany",upMachine.getUpJCompany());
    contentValues.put("upJBrand",upMachine.getUpJBrand());
    contentValues.put("upJVersion",upMachine.getUpJVersion());
    contentValues.put("upJIsBorder",upMachine.getUpJIsBorder());
    contentValues.put("upJIsTrue",upMachine.getUpJIsTrue());
    contentValues.put("upJBrief",upMachine.getUpJBrief());
    contentValues.put("upJUpType",upMachine.getUpJUpType());
    contentValues.put("upJUpTime",upMachine.getUpJUpTime());
    contentValues.put("upZCompany",upMachine.getUpZCompany());
    contentValues.put("upZBrand",upMachine.getUpZBrand());
    contentValues.put("upZVersion",upMachine.getUpZVersion());
    contentValues.put("upZIsBorder",upMachine.getUpZIsBorder());
    contentValues.put("upZIsTrue",upMachine.getUpZIsTrue());
    contentValues.put("upZUpTime",upMachine.getUpZUpTime());
    contentValues.put("upZUpType",upMachine.getUpZUpType());
    contentValues.put("upZBrief",upMachine.getUpZBrief());
    contentValues.put("upVIsInstall",upMachine.getUpVIsInstall());
    contentValues.put("upVCompany",upMachine.getUpVCompany());
    contentValues.put("upVBrand",upMachine.getUpVBrand());
    contentValues.put("upVVersion",upMachine.getUpVVersion());
    contentValues.put("upWIsInstall",upMachine.getUpWIsInstall());
    contentValues.put("upWBrand",upMachine.getUpWBrand());
    contentValues.put("upWCompany",upMachine.getUpWCompany());
    contentValues.put("upWVersion",upMachine.getUpWVersion());
    contentValues.put("upOBrief",upMachine.getUpOBrief());

    contentValues.put("downIOCompany",downMachine.getDownIOCompany());
    contentValues.put("downIOName",downMachine.getDownIOName());
    contentValues.put("downIOModle",downMachine.getDownIOModle());

    contentValues.put("seHCompany", server.getSe_h_company());
    contentValues.put("seHBrand", server.getSe_h_brand());
    contentValues.put("seHVersion", server.getSe_h_version());
    contentValues.put("seHIsBorder", server.getSe_h_isBorder());
    contentValues.put("seHBuyTime", server.getSe_h_buyTime());
    contentValues.put("seOsType", server.getSe_os_type());
    contentValues.put("seOsBrand", server.getSe_os_brand());
    contentValues.put("seOsIsTrue", server.getSe_os_isTrue());
    contentValues.put("seOsUpTime", server.getSe_os_upTime());
    contentValues.put("seOsUpType", server.getSe_os_upType());
    contentValues.put("seOsUpBrief", server.getSe_os_upBrief());
    contentValues.put("seDbCompany", server.getSe_db_company());
    contentValues.put("seDbName", server.getSe_db_name());
    contentValues.put("seDbVersion", server.getSe_db_version());
    contentValues.put("seDbIsBorder", server.getSe_db_isBorder());
    contentValues.put("seDbIsTrue", server.getSe_db_isTrue());
    contentValues.put("seDbUpTime", server.getSe_db_upTime());
    contentValues.put("seDbUpType", server.getSe_db_upType());
    contentValues.put("seDbBrief", server.getSe_db_brief());
    contentValues.put("seVIsInstall", server.getSe_v_isInstall());
    contentValues.put("seVCompany", server.getSe_v_company());
    contentValues.put("seVBrand", server.getSe_v_brand());
    contentValues.put("seVVersion", server.getSe_v_version());
    contentValues.put("seOBrief", server.getSe_o_brief());


    contentValues.put("swHCompany", mSwitch.getSw_h_company());
    contentValues.put("swHName", mSwitch.getSw_h_name());
    contentValues.put("swHType", mSwitch.getSw_h_type());
    contentValues.put("swHIsBorder", mSwitch.getSw_h_isBorder());
    contentValues.put("swSProtocol", mSwitch.getSw_s_protocol());

    contentValues.put("netHuIsConnect", netInfo.getNet_hu_isConnect());
    contentValues.put("netHuIsDefence", netInfo.getNet_hu_isDefence());
    contentValues.put("netVpn", netInfo.getNet_vpn());
    contentValues.put("netFirewall", netInfo.getNet_firewall());
    contentValues.put("netGFirewall", netInfo.getNet_gFirewall());
    contentValues.put("netIds", netInfo.getNet_ids());
    contentValues.put("netIps", netInfo.getNet_ips());
    contentValues.put("netOther", netInfo.getNet_other());
    contentValues.put("netOfficeIsConnect", netInfo.getNet_office_isConnect());
    contentValues.put("netOfficeIsDefence", netInfo.getNet_office_isDefence());
    contentValues.put("netOfficeFirewall", netInfo.getNet_office_firewall());
    contentValues.put("netOfficeOtherway", netInfo.getNet_office_otherWay());
    contentValues.put("netSIsConnect",netInfo.getNet_s_isConnect());
    contentValues.put("netSIsDefence",netInfo.getNet_s_isDefence());
    contentValues.put("netSVpn",netInfo.getNet_s_vpn());
    contentValues.put("netSFireWall",netInfo.getNet_s_firewall());
    contentValues.put("netSIds",netInfo.getNet_s_ids());
    contentValues.put("netSIps",netInfo.getNet_s_ips());
    contentValues.put("netSBrief",netInfo.getNet_s_brief());
    contentValues.put("netPIsConnect",netInfo.getNet_p_isConnect());
    contentValues.put("netPConnectType",netInfo.getNet_p_connectType());
    contentValues.put("netCloBrand",netInfo.getNet_clo_brand());
    contentValues.put("netCloIsConnect",netInfo.getNet_clo_isConnect());
    contentValues.put("netCloConnectType",netInfo.getNet_clo_connectType());
    contentValues.put("netOBrief",netInfo.getNet_o_brief());

    contentValues.put("otherBrief",other.getOther_brief());



    return contentValues;
  }


  /**
   * 根据行号生成不同cell
   *
   * @param rowNum 行号（从1开始）
   * @return
   * @throws Exception
   */
  public PdfPCell getTable(int rowNum) throws Exception {
    PdfPCell cell = new PdfPCell();
    switch (rowNum) {
      case 1:
        Paragraph paragraph = new Paragraph("鉴势 工控系统信息核查报告", fontChinese_1);
        //水平居中
        paragraph.setAlignment(Element.ALIGN_CENTER);
        //内边距
        cell.setPadding(CELL_PADDING_1);
        cell.setUseAscender(true);
        cell.addElement(paragraph);
        break;
      case 2:
        paragraph = new Paragraph("基本信息", fontChinese_3);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        cell.setPadding(CELL_PADDING_3);
        cell.setUseAscender(true);
        cell.addElement(paragraph);
        break;
      case 3:
        //cell内部嵌套table
        PdfPTable table = new PdfPTable(6);
        //设置table的宽度为100%
        table.setWidthPercentage(100);
        //设置不同列的宽度
        float[] columnWidths = {2f, 2f, 2f, 2f, 2f, 2f};
        table.setWidths(columnWidths);
        PdfPCell cell1 = new PdfPCell();
        cell1.setUseAscender(true);
        cell1.setPaddingTop(CELL_PADDING_2);
        cell1.setPaddingBottom(CELL_PADDING_2);
        Paragraph paragraph1 = new Paragraph("填表人", fontChinese_default);
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        cell1.addElement(paragraph1);

        PdfPCell cell2 = new PdfPCell();
        cell2.setUseAscender(true);
        cell2.setPaddingTop(CELL_PADDING_2);
        cell2.setPaddingBottom(CELL_PADDING_2);
        if(unit != null){
          Paragraph paragraph2 = new Paragraph(unit.getpName(), fontChinese_default);
          paragraph2.setAlignment(Element.ALIGN_CENTER);
          cell2.addElement(paragraph2);
        }else{
          Paragraph paragraph2 = new Paragraph("", fontChinese_default);
          paragraph2.setAlignment(Element.ALIGN_CENTER);
          cell2.addElement(paragraph2);
        }


        PdfPCell cell3 = new PdfPCell();
        cell3.setUseAscender(true);
        cell3.setPaddingTop(CELL_PADDING_2);
        cell3.setPaddingBottom(CELL_PADDING_2);
        Paragraph paragraph3 = new Paragraph("联系方式", fontChinese_default);
        paragraph3.setAlignment(Element.ALIGN_CENTER);
        cell3.addElement(paragraph3);

        PdfPCell cell4 = new PdfPCell();
        cell4.setUseAscender(true);
        cell4.setPaddingTop(CELL_PADDING_2);
        cell4.setPaddingBottom(CELL_PADDING_2);
        if(unit!=null){
          Paragraph paragraph4 = new Paragraph(unit.getPhoneNum(), fontChinese_default);
          paragraph4.setAlignment(Element.ALIGN_CENTER);
          cell4.addElement(paragraph4);
        }else{
          Paragraph paragraph4 = new Paragraph("", fontChinese_default);
          paragraph4.setAlignment(Element.ALIGN_CENTER);
          cell4.addElement(paragraph4);
        }


        PdfPCell cell5 = new PdfPCell();
        cell5.setUseAscender(true);
        cell5.setPaddingTop(CELL_PADDING_2);
        cell5.setPaddingBottom(CELL_PADDING_2);
        Paragraph paragraph5 = new Paragraph("检查日期", fontChinese_default);
        paragraph5.setAlignment(Element.ALIGN_CENTER);
        cell5.addElement(paragraph5);

        PdfPCell cell6 = new PdfPCell();
        cell6.setUseAscender(true);
        cell6.setPaddingTop(CELL_PADDING_2);
        cell6.setPaddingBottom(CELL_PADDING_2);
        Paragraph paragraph6 = new Paragraph("" + getDate(), fontChinese_default);
        paragraph6.setAlignment(Element.ALIGN_CENTER);
        cell6.addElement(paragraph6);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);

        //去掉cell和table之间的间隙
        cell.setPadding(0);
        cell.addElement(table);
        break;
      case 4:
        //cell内部嵌套table
        PdfPTable table_4 = new PdfPTable(2);
        //设置table的宽度为100%
        table_4.setWidthPercentage(100);
        //设置不同列的宽度
        float[] columnWidths1 = {2f, 10f};
        table_4.setWidths(columnWidths1);

        PdfPCell cell4_1 = new PdfPCell();
        cell4_1.setUseAscender(true);
        cell4_1.setPaddingTop(CELL_PADDING_2);
        cell4_1.setPaddingBottom(CELL_PADDING_2);
        Paragraph paragraph4_1 = new Paragraph("单位名称", fontChinese_default);
        paragraph4_1.setAlignment(Element.ALIGN_CENTER);
        cell4_1.addElement(paragraph4_1);

        PdfPCell cell4_2 = new PdfPCell();
        cell4_2.setUseAscender(true);
        cell4_2.setPaddingTop(CELL_PADDING_2);
        cell4_2.setPaddingBottom(CELL_PADDING_2);
        cell4_2.setPaddingLeft(CELL_PADDING_2);
        if(unit!=null){
          Paragraph paragraph4_2 = new Paragraph(unit.getName(), fontChinese_default);
          paragraph4_2.setAlignment(Element.ALIGN_LEFT);
          cell4_2.addElement(paragraph4_2);
        }else {
          Paragraph paragraph4_2 = new Paragraph("", fontChinese_default);
          paragraph4_2.setAlignment(Element.ALIGN_LEFT);
          cell4_2.addElement(paragraph4_2);
        }


        table_4.addCell(cell4_1);
        table_4.addCell(cell4_2);

        cell.setPadding(0);
        cell.addElement(table_4);
        break;
      case 5:
        //cell内部嵌套table
        PdfPTable table_5 = new PdfPTable(2);
        //设置table的宽度为100%
        table_5.setWidthPercentage(100);
        //设置不同列的宽度
        float[] columnWidths2 = {2f, 10f};
        table_5.setWidths(columnWidths2);

        PdfPCell cell5_1 = new PdfPCell();
        cell5_1.setUseAscender(true);
        cell5_1.setVerticalAlignment(cell5_1.ALIGN_MIDDLE);
        Paragraph paragraph5_1 = new Paragraph("系统功能简述", fontChinese_default);
        paragraph5_1.setAlignment(Element.ALIGN_CENTER);
        cell5_1.addElement(paragraph5_1);
        cell5_1.setFixedHeight(CELL_HEIGHT);

        PdfPCell cell5_2 = new PdfPCell();
        cell5_2.setUseAscender(true);
        cell5_2.setPadding(CELL_PADDING_2);
        if(unit!=null){
          Paragraph paragraph5_2 = new Paragraph(unit.getBrief(), fontChinese_default);
          paragraph5_2.setAlignment(Element.ALIGN_LEFT);
          cell5_2.addElement(paragraph5_2);
        }else {
          Paragraph paragraph5_2 = new Paragraph("", fontChinese_default);
          paragraph5_2.setAlignment(Element.ALIGN_LEFT);
          cell5_2.addElement(paragraph5_2);
        }


        table_5.addCell(cell5_1);
        table_5.addCell(cell5_2);

        cell.setPadding(0);
        cell.addElement(table_5);
        break;
      case 6:
        paragraph = new Paragraph("工控设备信息", fontChinese_3);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        cell.setPadding(CELL_PADDING_3);
        cell.setUseAscender(true);
        cell.addElement(paragraph);
        break;
      case 7:
        //cell内部嵌套table
        PdfPTable table8 = new PdfPTable(4);
        //设置table的宽度为100%
        table8.setWidthPercentage(100);
        //设置不同列的宽度
        float[] columnWidth8 = {1f, 1.5f, 1.5f, 6f};
        table8.setWidths(columnWidth8);

        PdfPCell cell8_1 = new PdfPCell();
        cell8_1.setUseAscender(true);
        cell8_1.setVerticalAlignment(cell8_1.ALIGN_MIDDLE);
        Paragraph paragraph8_1 = new Paragraph("序号", fontChinese_default);
        paragraph8_1.setAlignment(Element.ALIGN_CENTER);
        cell8_1.addElement(paragraph8_1);
        cell8_1.setVerticalAlignment(cell8_1.ALIGN_MIDDLE);
        cell8_1.setFixedHeight(20);

        PdfPCell cell8_2 = new PdfPCell();
        cell8_2.setUseAscender(true);
        Paragraph paragraph8_2 = new Paragraph("类别", fontChinese_default);
        paragraph8_2.setAlignment(Element.ALIGN_CENTER);
        cell8_2.addElement(paragraph8_2);
        cell8_2.setVerticalAlignment(cell8_1.ALIGN_MIDDLE);
        cell8_2.setFixedHeight(20);

        PdfPCell cell8_3 = new PdfPCell();
        cell8_3.setUseAscender(true);
        Paragraph paragraph8_3 = new Paragraph("数量", fontChinese_default);
        paragraph8_3.setAlignment(Element.ALIGN_CENTER);
        cell8_3.addElement(paragraph8_3);
        cell8_3.setVerticalAlignment(cell8_1.ALIGN_MIDDLE);
        cell8_3.setFixedHeight(20);

        PdfPCell cell8_4 = new PdfPCell();
        cell8_4.setUseAscender(true);
        Paragraph paragraph8_4 = new Paragraph("备注", fontChinese_default);
        paragraph8_4.setAlignment(Element.ALIGN_CENTER);
        cell8_4.addElement(paragraph8_4);
        cell8_4.setVerticalAlignment(cell8_1.ALIGN_MIDDLE);
        cell8_4.setFixedHeight(20);


        table8.addCell(cell8_1);
        table8.addCell(cell8_2);
        table8.addCell(cell8_3);
        table8.addCell(cell8_4);

        //去掉cell和table之间的间隙
        cell.setPadding(0);
        cell.addElement(table8);
        break;
      case 8:
        String[]  types = {"SCADA","PLC","DCS"};
        String[]  sum = {"10","5","8"};
        String[]  briefs = {"用于工控用途","用于工控用途","用于工控用途"};
        for(int i = 0;i<types.length;i++){
          PdfPTable table9 = new PdfPTable(4);
          //设置table的宽度为100%
          table9.setWidthPercentage(100);
          //设置不同列的宽度
          float[] columnWidth9 = {1f, 1.5f, 1.5f, 6f};
          table9.setWidths(columnWidth9);

          PdfPCell cell9_1 = new PdfPCell();
          cell9_1.setUseAscender(true);
          cell9_1.setVerticalAlignment(cell9_1.ALIGN_MIDDLE);
          cell9_1.setPadding(CELL_PADDING_8);
          Paragraph paragraph800_1 = new Paragraph(i+1+"", fontChinese_default);
          paragraph800_1.setAlignment(Element.ALIGN_CENTER);
          cell9_1.addElement(paragraph800_1);
          cell9_1.setVerticalAlignment(cell9_1.ALIGN_MIDDLE);

          PdfPCell cell9_2 = new PdfPCell();
          cell9_2.setUseAscender(true);
          Paragraph paragraph9_2 = new Paragraph(types[i], fontChinese_default);
          paragraph9_2.setAlignment(Element.ALIGN_CENTER);
          cell9_2.addElement(paragraph9_2);
          cell9_2.setVerticalAlignment(cell9_1.ALIGN_MIDDLE);
          cell9_2.setPadding(CELL_PADDING_8);

          PdfPCell cell9_3 = new PdfPCell();
          cell9_3.setUseAscender(true);
          Paragraph paragraph9_3 = new Paragraph(sum[i], fontChinese_default);
          paragraph9_3.setAlignment(Element.ALIGN_CENTER);
          cell9_3.addElement(paragraph9_3);
          cell9_3.setVerticalAlignment(cell9_1.ALIGN_MIDDLE);
          cell9_3.setPadding(CELL_PADDING_8);

          PdfPCell cell9_4 = new PdfPCell();
          cell9_4.setUseAscender(true);
          Paragraph paragraph9_4 = new Paragraph(briefs[i], fontChinese_default);
          paragraph9_4.setAlignment(Element.ALIGN_CENTER);
          cell9_4.addElement(paragraph9_4);
          cell9_4.setVerticalAlignment(cell9_1.ALIGN_MIDDLE);
          cell9_4.setPadding(CELL_PADDING_8);

          table9.addCell(cell9_1);
          table9.addCell(cell9_2);
          table9.addCell(cell9_3);
          table9.addCell(cell9_4);

          //去掉cell和table之间的间隙
          cell.setPadding(0);
          cell.addElement(table9);
        }
        break;
      case 9:
        paragraph = new Paragraph("系统漏洞详情", fontChinese_3);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        cell.setPadding(CELL_PADDING_3);
        cell.setUseAscender(true);
        cell.addElement(paragraph);
        break;


      case 10:
        //cell内部嵌套table
        PdfPTable table7 = new PdfPTable(5);
        //设置table的宽度为100%
        table7.setWidthPercentage(100);
        //设置不同列的宽度
        float[] columnWidth7 = {1f, 2f, 1f, 3f, 3f};
        table7.setWidths(columnWidth7);

        PdfPCell cell7_1 = new PdfPCell();
        cell7_1.setUseAscender(true);
        cell7_1.setVerticalAlignment(cell7_1.ALIGN_MIDDLE);
        Paragraph paragraph7_1 = new Paragraph("序号", fontChinese_default);
        paragraph7_1.setAlignment(Element.ALIGN_CENTER);
        cell7_1.addElement(paragraph7_1);
        cell7_1.setVerticalAlignment(cell7_1.ALIGN_MIDDLE);
        cell7_1.setFixedHeight(20);

        PdfPCell cell7_3 = new PdfPCell();
        cell7_3.setUseAscender(true);
        Paragraph paragraph7_3 = new Paragraph("标题", fontChinese_default);
        paragraph7_3.setAlignment(Element.ALIGN_CENTER);
        cell7_3.addElement(paragraph7_3);
        cell7_3.setVerticalAlignment(cell7_1.ALIGN_MIDDLE);
        cell7_3.setFixedHeight(20);

        PdfPCell cell7_5 = new PdfPCell();
        cell7_5.setUseAscender(true);
        Paragraph paragraph7_5 = new Paragraph("等级", fontChinese_default);
        paragraph7_5.setAlignment(Element.ALIGN_CENTER);
        cell7_5.addElement(paragraph7_5);
        cell7_5.setVerticalAlignment(cell7_1.ALIGN_MIDDLE);
        cell7_5.setFixedHeight(20);

        PdfPCell cell7_6 = new PdfPCell();
        cell7_6.setUseAscender(true);
        Paragraph paragraph7_6 = new Paragraph("漏洞描述", fontChinese_default);
        paragraph7_6.setAlignment(Element.ALIGN_CENTER);
        cell7_6.addElement(paragraph7_6);
        cell7_6.setVerticalAlignment(cell7_1.ALIGN_MIDDLE);
        cell7_6.setFixedHeight(20);

        PdfPCell cell7_7 = new PdfPCell();
        cell7_7.setUseAscender(true);
        Paragraph paragraph7_7 = new Paragraph("影响产品", fontChinese_default);
        paragraph7_7.setAlignment(Element.ALIGN_CENTER);
        cell7_7.addElement(paragraph7_7);
        cell7_7.setVerticalAlignment(cell7_1.ALIGN_MIDDLE);
        cell7_7.setFixedHeight(20);

        table7.addCell(cell7_1);
        table7.addCell(cell7_3);
        table7.addCell(cell7_5);
        table7.addCell(cell7_6);
        table7.addCell(cell7_7);

        //去掉cell和table之间的间隙
        cell.setPadding(0);
        cell.addElement(table7);
        break;
      case 11:
        //cell内部嵌套table
        FlawDb flawDb = new FlawDb();
        ArrayList<Flaw> list = flawDb.queryTag();

        for(int i = 0;i<list.size();i++){
          PdfPTable table800 = new PdfPTable(5);
          //设置table的宽度为100%
          table800.setWidthPercentage(100);
          table800.setSplitLate(false);
          table800.setSplitRows(true);
          //设置不同列的宽度
          float[] columnWidth800 = {1f, 2f, 1f, 3f, 3f};
          table800.setWidths(columnWidth800);

          PdfPCell cell800_1 = new PdfPCell();
          cell800_1.setUseAscender(true);
          cell800_1.setVerticalAlignment(cell800_1.ALIGN_MIDDLE);
          cell800_1.setPadding(CELL_PADDING_8);
          Paragraph paragraph800_1 = new Paragraph(i+1+"", fontChinese_default);
          paragraph800_1.setAlignment(Element.ALIGN_CENTER);
          cell800_1.addElement(paragraph800_1);
          cell800_1.setVerticalAlignment(cell800_1.ALIGN_MIDDLE);


          PdfPCell cell800_2 = new PdfPCell();
          cell800_2.setUseAscender(true);
          Paragraph paragraph800_2 = new Paragraph(list.get(i).getTitle(), fontChinese_default);
          paragraph800_2.setAlignment(Element.ALIGN_CENTER);
          cell800_2.addElement(paragraph800_2);
          cell800_2.setVerticalAlignment(cell800_1.ALIGN_MIDDLE);
          cell800_2.setPadding(CELL_PADDING_8);



          PdfPCell cell800_3 = new PdfPCell();
          cell800_3.setUseAscender(true);
          Paragraph paragraph800_3 = new Paragraph(list.get(i).getRank(), fontChinese_default);
          paragraph800_3.setAlignment(Element.ALIGN_CENTER);
          cell800_3.addElement(paragraph800_3);
          cell800_3.setVerticalAlignment(cell800_1.ALIGN_MIDDLE);
          cell800_3.setPadding(CELL_PADDING_8);



          PdfPCell cell800_4 = new PdfPCell();
          cell800_4.setUseAscender(true);
          Paragraph paragraph800_4 = new Paragraph(list.get(i).getDesc(), fontChinese_default);
          paragraph800_4.setAlignment(Element.ALIGN_CENTER);
          cell800_4.addElement(paragraph800_4);
          cell800_4.setVerticalAlignment(cell800_1.ALIGN_MIDDLE);
          cell800_4.setPadding(CELL_PADDING_8);


          PdfPCell cell800_5 = new PdfPCell();
          cell800_5.setUseAscender(true);
          Paragraph paragraph8_5 = new Paragraph(list.get(i).getInfluence(), fontChinese_default);
          paragraph8_5.setAlignment(Element.ALIGN_CENTER);
          cell800_5.addElement(paragraph8_5);
          cell800_5.setVerticalAlignment(cell800_1.ALIGN_MIDDLE);
          cell800_5.setPadding(CELL_PADDING_8);


          table800.addCell(cell800_1);
          table800.addCell(cell800_2);
          table800.addCell(cell800_3);
          table800.addCell(cell800_4);
          table800.addCell(cell800_5);

          //去掉cell和table之间的间隙
          cell.setPadding(0);
          cell.addElement(table800);
        }

        break;
      case 12:
        paragraph = new Paragraph("结论与建议", fontChinese_3);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        cell.setPadding(CELL_PADDING_3);
        cell.setUseAscender(true);
        cell.addElement(paragraph);
        break;
      case 13:
        Paragraph paragraph10_1 = new Paragraph("综上，该系统主要存在以下问题，针对问题提出的建议如下", fontChinese_default);
        paragraph10_1.setAlignment(Element.ALIGN_LEFT);
        Paragraph paragraph10_2 = new Paragraph("1. 高危漏洞较多，系统存在风险，部门应做好安全防护工作", fontChinese_default);
        paragraph10_2.setAlignment(Element.ALIGN_LEFT);
        Paragraph paragraph10_3 = new Paragraph("2. 系统可以优化，将部分软件升级到最新版本以修复漏洞", fontChinese_default);
        paragraph10_3.setAlignment(Element.ALIGN_LEFT);
        Paragraph paragraph10_4 = new Paragraph("3. 接入网络情况复杂，禁止接入互联网", fontChinese_default);
        paragraph10_4.setAlignment(Element.ALIGN_LEFT);
        cell.setPadding(CELL_PADDING_3);

        cell.addElement(paragraph10_1);
        cell.addElement(paragraph10_2);
        cell.addElement(paragraph10_3);
        cell.addElement(paragraph10_4);
        break;

    }

    return cell;
  }

  public static String getDate() {
    return format.format(new Date());
  }
}
