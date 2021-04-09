package com.rousetime.trace_plugin.method

class MethodCell(
        var name: String, // 原方法名
        var desc: String, // 原方法描述
        var parent: String, // 方法所在的接口或类
        var agentName: String, // 采集数据的方法名
        var agentDesc: String, // 采集数据的方法描述
        var paramsStart: Int, // 采集数据的方法参数起始索引（ 0：this，1+：普通参数 ）
        var paramsCount: Int, // 采集数据的方法参数个数
        var opcodes: IntArray // 参数类型对应的ASM指令，加载不同类型的参数需要不同的指令
        /*
            ('I',Opcodes.ILOAD);// I: int , retrieve integer from local variable
            ('Z',Opcodes.ILOAD);// Z: bool , retrieve integer from local variable
            ('J',Opcodes.LLOAD);// J: long , retrieve long from local variable
            ('F',Opcodes.FLOAD);// F: float , retrieve float from local variable
            ('D',Opcodes.DLOAD);// D: double , retrieve double from local variable
        */
)