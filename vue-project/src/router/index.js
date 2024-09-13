import { createRouter,createWebHistory } from "vue-router";
import LoginMain from '../views/Login/LoginMain.vue';
import LoginBox from '../views/Login/LoginBox.vue'
import RegisterBox from '../views/Login/RegisterBox.vue'
// 首页
import HomePage from '../components/HomePage.vue'
// 修改密码
import ChangePassword from '../components/ChangePassword.vue';
// 系统消息
import SysMessage from '../components/SysMessage.vue'
// 教师页面
import TeacherPage from '../views/Main/body/Teacher/TeacherPage.vue';
import PersonalInformation from '../views/Main/body/Teacher/content/childComponents/PersonalInformation.vue';
import PrimaryQue from '../views/Main/body/Teacher/content/childComponents/PrimaryQue.vue';
import MultiChoice from '../views/Main/body/Teacher/content/childComponents/MultiChoice.vue';
import StudentList from '../views/Main/body/Teacher/content/childComponents/StudentList.vue';
import  IncompleteResult from '../views/Main/body/Teacher/content/childComponents/resultOutput/IncompleteResult.vue';
import  CompleteResult from '../views/Main/body/Teacher/content/childComponents/resultOutput/CompleteResult.vue';
// 学生页面
import StudentPage from '../views/Main/body/Student/StudentPage.vue';
import PersonCenter from '../views/Main/body/Student/content/childComponents/PersonCenter.vue';
import StuPersonInf from '../views/Main/body/Student/content/childComponents/StuPersonInf.vue';
import intentionFillMain from '../views/Main/body/Student/content/childComponents/intentionFillMain.vue';
import EarlyDecision from '../views/Main/body/Student/content/childComponents/EarlyDecision.vue';
import TeacherInvitation from '../views/Main/body/Student/content/childComponents/teacherInvitation.vue';
import FillDecision from '../views/Main/body/Student/content/childComponents/FillDecision.vue';
import TeacherList from '../views/Main/body/Student/content/childComponents/TeacherList.vue';
import TeacherInfo from '../views/Main/body/Student/content/childComponents/TeacherInfo.vue';
import CheckResult from '../views/Main/body/Student/content/childComponents/CheckResult.vue';
// 管理员页面
import AdministratorPage from '../views/Main/body/Administrator/AdministratorPage.vue';
import DistributeNum from '../views/Main/body/Administrator/content/childComponents/DistributeNum.vue';
import IntentionProcess from '../views/Main/body/Administrator/content/childComponents/IntentionProcess.vue';
import MatchResult from '../views/Main/body/Administrator/content/childComponents/MatchResult.vue';
import StudentMatch from '../views/Main/body/Administrator/content/childComponents/matchResult/StudentMatch.vue';
import TeacherMatch from '../views/Main/body/Administrator/content/childComponents/matchResult/TeacherMatch.vue';
import FirstFeedback from '../views/Main/body/Administrator/content/childComponents/matchResult/FirstFeedback.vue';
const routes = [
    // 登录注册页面
    {
        path: '/',
        name: 'loginPage',
        component:LoginMain,
        children: [
            {
                path: '/',
                name: 'login',
                component: LoginBox,
            },
            {
                path: '/register',
                name: 'register',
                component: RegisterBox,
            }
        ]
    },
    // 教师端页面
    {
        path: '/teacher',
        component: TeacherPage,
        children: [
            // 首页
            {
                path: '',
                component: HomePage,
            },
            // 修改密码
            {
                path:'/teacher/changePassword',
                component: ChangePassword,
            },
            // 个人信息
            {
                path: '/teacher/personalInformation',
                component: PersonalInformation,
            },
            // 提前批
            {
                path: '/teacher/primaryQue',
                component: PrimaryQue,
            },
            // 双选匹配
            {
                path: '/teacher/multiChoice',
                component: MultiChoice,
            },
            {
                path: '/teacher/studentList/:id',
                component: StudentList,
            },
            // 结果导出
            {
                path: '/teacher/incompleteResult',
                component: IncompleteResult,
            },
            {
                path: '/teacher/completeResult',
                component: CompleteResult,
            },
            // 系统消息
            {
                path: '/teacher/sysMessage',
                component: SysMessage,
            }
        ]
    },
    // 学生端页面
    {
        path: '/student',
        component: StudentPage,
        children: [
            // 首页
            {
                path: '',
                component: HomePage,
            },
            // 个人中心
            {
                path: '/student/personCenter',
                component: PersonCenter,
            },
            // 修改密码
            {
                path: '/student/changePassword',
                component: ChangePassword,
            },
            // 个人信息
            {
                path: '/student/stuPersonInf',
                component: StuPersonInf,
            },
            //教师信息
            {
                path: '/student/teacherList',
                component: TeacherList,
            },
            {
                path: '/student/teacherDetail/:username',
                component: TeacherInfo,
            },
            // 意向填写
            {
                path: '/student/intentionFillMain',
                component: intentionFillMain
            },
            {
                path: '/student/earlyDecision',
                component: EarlyDecision,
            },
            {
                path: '/student/teacherInvitation',
                component: TeacherInvitation,
            },
            {
                path: '/student/fillDecision',
                component: FillDecision,
            },
            // 匹配结果查看
            {
                path: '/student/checkResult',
                component: CheckResult,
            },
            // 系统消息
            {
                path: '/student/sysMessage',
                component: SysMessage,
            }
        ]
    },
    // 管理端页面
    {
        path: '/administrator',
        component: AdministratorPage,
        children: [
            // 首页
            {
                path: '',
                component: HomePage,
            },
            // 修改密码
            {
                path: '/administrator/changePassword',
                component: ChangePassword,
            },
            // 分配名额
            {
                path: '/administrator/distributeNum',
                component: DistributeNum,
            },
            // 意向进程
            {
                path: '/administrator/intentionProcess',
                component: IntentionProcess,
            },
            // 匹配结果查看
            {
                path: '/administrator/matchResult',
                component: MatchResult,
            },
            {
                path: '/administrator/studentMatch',
                component: StudentMatch,
            },
            {
                path: '/administrator/teacherMatch',
                component: TeacherMatch,
            },
            {
                path: '/administrator/firstFeedback',
                component: FirstFeedback,
            },
            // 系统消息
            {
                path: '/administrator/sysMessage',
                component: SysMessage,
            }
        ]
    }
]
const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router