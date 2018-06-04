using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Threading.Tasks;

namespace musicApplication
{
    [ServiceContract]
    public interface IService
    {
        [OperationContract]
        [WebInvoke]
        List<String> EchoWithPost(string s);
    }
}
