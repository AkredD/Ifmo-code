using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;

namespace musicApplication
{
    class Formation
    {
        public static XmlDocument form()
        {
            XDocument request = new XDocument();
            XElement compositions = new XElement("compositions");
            List<Item> items = Data.getInstance().items;
            foreach(Item a in items)
            {
                XElement composition = new XElement("composition");
                XAttribute fullName = new XAttribute("name", a.getSinger() + " - " + a.getName());
                XElement singer = new XElement("singer", a.getSinger());
                XElement name = new XElement("name", a.getName());
                XElement path = new XElement("path", a.getPath());
                XElement length = new XElement("length", a.getLength());
                composition.Add(fullName);
                composition.Add(singer);
                composition.Add(name);
                composition.Add(path);
                composition.Add(length);
                compositions.Add(composition);
            }
            request.Add(compositions);
            //request.Save("send")
            return ToXmlDocument(request);
        }

        public static XmlDocument ToXmlDocument(XDocument xDocument)
        {
            var xmlDocument = new XmlDocument();
            using (var xmlReader = xDocument.CreateReader())
            {
                xmlDocument.Load(xmlReader);
            }
            return xmlDocument;
        }
    }
}
