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
    /// Логика взаимодействия для ServerService.xaml
    /// </summary>
    public partial class ServerService : Window
    {
        public ServerService()
        {
            InitializeComponent();
            Server.getInstance();
            portBox.Text = Server.port;
            if (Server.listener.IsListening) button.Content = "Stop";
        }

        public void clickServer(object sender, RoutedEventArgs e)
        {
            if (button.Content.ToString() == "Start")
            {
                string portS = portBox.Text;
                foreach (Char a in portS)
                    if (!(a >= '1' && a <= '9' || a == '0'))
                    {
                        MessageBox.Show("port can contains only digits");
                        return;
                    }
                if (portS.Length > 5 || portS.Length < 4)
                {
                    MessageBox.Show("please enter 4 or 5 digits");
                    return;
                }
                try
                {
                    Server.start(portS);
                    button.Content = "Stop";
                }
                catch
                {
                    MessageBox.Show("wrong port");
                }
            }
            else
            {
                button.Content = "Start";
                Server.getInstance().stop();
            }
        }
    }
}
