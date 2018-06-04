using Microsoft.Win32;
using musicApplication;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace MusicApplicationWPF.gui
{
    /// <summary>
    /// Логика взаимодействия для WindowAdd.xaml
    /// </summary>
    public partial class WindowAdd : Window
    {
        public WindowAdd()
        {
            InitializeComponent();
        }

        private void textBox_Copy_TextChanged(object sender, TextChangedEventArgs e)
        {

        }

        public void clickAdd(object sende, RoutedEventArgs e)
        {
            if (textBoxSinger.Text != "" && textBoxName.Text != "" && textBoxPath.Text != "")
            {
                Data.getInstance().add(textBoxSinger.Text, textBoxName.Text, textBoxPath.Text);
                this.Close();
                return;
            }
            MessageBox.Show("Please, fill fields");
        }

        //open dialog
        private void button_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog dlg = new OpenFileDialog();
            dlg.CheckFileExists = true;
            dlg.Filter = "MP3 files (*.mp3)|*.mp3";
            //dlg.DefaultExt = ".mp3";
            if (dlg.ShowDialog() == true)
            {
                // Get the selected file's path from the dialog.
                this.textBoxPath.Text = dlg.FileName;

            }
        }

    }
    
}
