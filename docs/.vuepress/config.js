module.exports = {
    title: '轻量化健康打卡自动提醒机器人',
    description: '疫情之下辅助班级每日健康打卡提醒的好帮手！',
    themeConfig: {
        nav: [
            {text: 'Home', link: '/'},                      // 根路径
            {
                text: 'Guide',
                items: [
                    {text: '简介', link: '/guide/'},
                    {text: '稳定版', link: '/guide/stable/'},
                    {text: 'FUTURE版', link: '/guide/future/'},
                ]
            },
            {text: 'External', link: 'https://google.com'}, // 外部链接
            // 显示下拉列表
            {
                text: 'Languages',
                items: [
                    {text: 'Chinese', link: '/language/chinese'},
                    {text: 'Japanese', link: '/language/japanese'}
                ]
            },
            // 下拉列表显示分组
            {
                text: '高级',
                items: [
                    {
                        text: '算法',
                        items: [
                            {text: '冒泡', link: '/language/chinese'},
                            {text: '快速', link: '/language/japanese'}
                        ]
                    },
                    {
                        text: '设计模式',
                        items: [
                            {text: '工厂', link: '/language/chinese'},
                            {text: '单例', link: '/language/chinese'},
                        ]
                    },
                ]
            }
        ]
    }
}