using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace musicApplication
{
    class client
    {//mvvm
        public static XmlDocument download(String url)
        {
            XmlDocument req = new XmlDocument();
            WebRequest request = WebRequest.Create(url);
            request.Method = "POST";
            request.ContentType = "text/xml";
            StreamWriter writer = new StreamWriter(request.GetRequestStream());
            WebResponse resp = request.GetResponse();
            StreamReader reader = new StreamReader(resp.GetResponseStream());
            String responce = reader.ReadToEnd();
            Data.getInstance().items = Parser.parse(responce);
            Data.getInstance().mainForm.setItems();
            //resp.
            //writer. 
            //writer.Write()

            /*var client = new WebClient();
            Console.WriteLine("Requesting...");
            var result = client.DownloadString(url);
            //request = result;
            Console.WriteLine(result);*/
            return req;
        }
    }
}
