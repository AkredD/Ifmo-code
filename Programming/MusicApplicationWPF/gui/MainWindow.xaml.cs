using Microsoft.Win32;
using musicApplication;
using MusicApplicationWPF.gui;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Drawing;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace MusicApplicationWPF
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            Data.getInstance().mainForm = this;
            InitializeComponent();
        }

        public void setItems()
        {
            List<Item> items = Data.getInstance().items;
            listBox.Items.Clear();
            foreach (Item a in items) listBox.Items.Add(a.ToString());
        }

        public void addElem(string fullname)
        {
            listBox.Items.Add(fullname);
        }

        public void delElem()
        {
            if (listBox.SelectedItem != null)
            {
                listBox.Items.Remove(listBox.SelectedItem);
            }
        }

        public void clickNewFile(object sender, RoutedEventArgs e)
        {
            Data.getInstance().items = new List<Item>();
            setItems();
        }

        public void clickOpenPLS(object sender, RoutedEventArgs e)
        {
            OpenFileDialog dlg = new OpenFileDialog();
            dlg.CheckFileExists = true;
            dlg.RestoreDirectory = true;
            dlg.Filter = "PLS files (*.pls)|*.pls";
            //dlg.DefaultExt = ".mp3";
            if (dlg.ShowDialog() == true)
            {
                // Get the selected file's path from the dialog.
                try
                {
                    Data.getInstance().openPLS(dlg.FileName);
                }catch(Exception exc)
                {
                    MessageBox.Show("wrong file");
                }

            }
        }

        public void savePLSfile(object sender, RoutedEventArgs e)
        {
            SaveFileDialog dlg = new SaveFileDialog();
            dlg.Filter = "PLS files (*.pls)|*.pls";
            dlg.RestoreDirectory = true;
            if (dlg.ShowDialog().Value)
            {
                try
                {
                    Data.getInstance().savePLS(dlg.FileName);
                }catch(Exception exc)
                {
                    MessageBox.Show("wrong path");
                }

            }
            
        }

        public void clickButtonAdd(object sender, RoutedEventArgs e)
        {
            WindowAdd form = new WindowAdd();
            form.Show();
            
        }

        public void clickButtonDelete(object sender, RoutedEventArgs e)
        {
            delElem();
        }

        public void ckickButtonEdit(object sender, RoutedEventArgs e)
        {

        }

        public void clickButtonSearch(object sender, RoutedEventArgs e)
        {
            List<Item> itemsSearch = Data.getInstance().search(textBoxSearch.Text);
            listBox.Items.Clear();
            foreach (Item a in itemsSearch) listBox.Items.Add(a.ToString());

        }

        public void clickButtonCancelSearch(object sender, RoutedEventArgs e)
        {
            setItems();
        }

        public void clickEdit(object sender, RoutedEventArgs e)
        {
            Edit form = new Edit(listBox.SelectedItem.ToString());
            form.Show();
        }

        public void clickServerService(object sender, RoutedEventArgs e)
        {
            ServerService form = new ServerService();
            form.Show();
        }

        public void clickDownload(object sender, RoutedEventArgs e)
        {
            DownloadService form = new DownloadService();
            form.Show();
        }
    }
}
