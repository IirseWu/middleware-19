//------------------------------------------------------------------------------
// <auto-generated>
//     此代码已从模板生成。
//
//     手动更改此文件可能导致应用程序出现意外的行为。
//     如果重新生成代码，将覆盖对此文件的手动更改。
// </auto-generated>
//------------------------------------------------------------------------------

namespace moocweb.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class stu_ans_score
    {
        public long stu_id { get; set; }
        public long pq_id { get; set; }
        public long ec_id { get; set; }
        public Nullable<decimal> score { get; set; }
        public Nullable<System.TimeSpan> cost { get; set; }
    }
}
