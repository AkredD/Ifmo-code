using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;

namespace musicApplication
{
    class Parser
    {
        public static List<Item> parse(String xmlString)
        {
            XmlDocument responce = new XmlDocument();
            responce.LoadXml(xmlString);
            List<Item> items = new List<Item>();
            XmlElement root = responce.DocumentElement;
            foreach(XmlNode node in root)
            {
                String singer = "";
                String name = "";
                String path = "";
                String length = "";
                foreach (XmlNode elem in node.ChildNodes)
                {
                    switch (elem.Name)
                    {
                        case "singer":
                            singer = elem.InnerText;
                            break;
                        case "name":
                            name = elem.InnerText;
                            break;
                        case "path":
                            path = elem.InnerText;
                            break;
                        case "length":
                            length = elem.InnerText;
                            items.Add(new Item(singer, name, path, length));
                            break;
                    }
                    
                }
            }

            return items;
        }
    }
}
