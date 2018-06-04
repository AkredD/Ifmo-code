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
    /// Логика взаимодействия для DownloadService.xaml
    /// </summary>
    public partial class DownloadService : Window
    {
        public DownloadService()
        {
            InitializeComponent();
        }

        public void clickDownload(object sender, RoutedEventArgs e)
        {
            string url = urlBox.Text;
            if (url == "")
            {
                MessageBox.Show("Please, enter URL");
                return;
            }

            try
            {
                client.download(url);
                this.Close();
                return;
            }catch(Exception exc)
            {
                MessageBox.Show("Wrong URL");
                return;
            }
        }
    }
}
