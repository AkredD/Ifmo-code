using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Xml;

namespace musicApplication
{
    class Server
    {
        private static Server instance;
        public static HttpListener listener = new HttpListener();
        public static string port { get; private set; } = "";


        private Server()
        {
        }

        public static Server getInstance()
        {
            if (instance == null)
            {
                instance = new Server();
            }
            
            return instance;
        }

        public void stop()
        {
            listener.Stop();
            listener = new HttpListener();
        }

        public static void start(String portL)
        {
            port = portL;
            try
            {
                Listen();
            }
            catch
            {
                throw new Exception("wrong port");
            }
        }

        private static async Task Listen()
        {
            listener = new HttpListener();
            listener.Prefixes.Add("http://localhost:" + port + "/");
            listener.Start();
            Console.WriteLine("Ожидание подключений...");
            while (listener.IsListening)
            {
                HttpListenerContext context = await listener.GetContextAsync();
                HttpListenerRequest request = context.Request;
                HttpListenerResponse response = context.Response;
                if (request.HttpMethod == "POST")
                {
                    //request.Url
                }
                response.ContentType = "text/xml";
                context.Response.ContentEncoding = System.Text.Encoding.UTF8;
                Stream output = response.OutputStream;
                Formation.form().Save(output);
                output.Close();
            }
        }
    }
}
