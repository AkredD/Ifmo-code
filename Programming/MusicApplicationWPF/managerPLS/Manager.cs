using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace musicApplication
{
    class Manager
    {
        private static Manager instance;

        private Manager()
        {
        }

        public static Manager getInstance()
        {
            if (instance == null)
            {
                instance = new Manager();
            }
            return instance;
        }

        public void savePLS(String path)
        {
            String pathTo = path;
            StreamWriter writer = File.CreateText(pathTo);
            writer.WriteLine("[playlist]");
            List<Item> list = Data.getInstance().items;
            for (int i = 1; i <= list.Count; i++)
            {
                for (int j = 0; j < 6; ++j)
                {
                    switch (j)
                    {
                        case 1:
                            writer.WriteLine("File" + i + "=" + list[i - 1].getPath());
                            break;
                        case 3:
                            writer.WriteLine("Title" + i + "=" + list[i - 1].getSinger() + "-" + list[i - 1].getName());
                            break;
                        case 5:
                            writer.WriteLine("Length" + i + "=" + list[i - 1].getLength());
                            break;
                        default:
                            writer.WriteLine("");
                            break;
                    }
                }
            }
            writer.WriteLine("");
            writer.WriteLine("NumberOfEntries=" + list.Capacity);
            writer.WriteLine("");
            writer.WriteLine("Version=2");
            writer.Close();
        }

        public List<Item> openPLS(String path)
        {
            FileStream file = new FileStream(path, FileMode.Open);
            StreamReader reader = new StreamReader(file);
            List<Item> items = parser(reader);
            return items;
        }

        private List<Item> parser(StreamReader reader)
        {
            List<Item> items = new List<Item>();
            String nameCatalog = reader.ReadLine();
            String space;
            int j = 1;
            while (!reader.EndOfStream)
            {
                int len = (j.ToString()).Length;
                String path = "";
                String singer = "";
                String name = "";
                String length = "";
                for (int i = 0; i < 6; i++)
                {
                    if (reader.EndOfStream) break;
                    switch (i)
                    {
                        case 1:
                            String s = reader.ReadLine();
                            path = s.Substring(s.IndexOf("=") + 1);
                            break;
                        case 3:
                            String ss = reader.ReadLine();
                            String fullName = ss.Substring(ss.IndexOf("=") + 1);
                            String[] split = fullName.Split(new Char[] { '-' });
                            switch (split.Length)
                            {
                                case 1:
                                    singer = "";
                                    name = split[0];
                                    break;
                                case 2:
                                    singer = split[0];
                                    name = split[1];
                                    break;
                                default:
                                    singer = split[0];
                                    name = "";
                                    for (int c = 1; c < split.Length; ++c)
                                    {
                                        name += split[c];
                                    }
                                    break;

                            }
                            break;

                        case 5:
                            String sss = reader.ReadLine();
                            sss = sss.Substring(sss.IndexOf("=") + 1);
                            length = sss;
                            items.Add(new Item(singer, name, path, length));
                            break;
                        default:
                            space = reader.ReadLine();
                            break;

                    }

                }
            }

            return items;
        }


    }
}
