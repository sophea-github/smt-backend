package Stock.smt.config

import Stock.smt.model.Custom.ResponseObjectMap
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.mail.javamail.JavaMailSender

@Configuration
class AppConfig {
    @Bean
    fun responseObjectMap() = ResponseObjectMap()

    @Bean
    @Primary
    fun getTaskExecutor(): TaskExecutor? {
        val threadPoolTaskExecutor = ThreadPoolTaskExecutor()
        threadPoolTaskExecutor.initialize()
        threadPoolTaskExecutor.corePoolSize = 1
        threadPoolTaskExecutor.maxPoolSize = 5
        /*threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true)
        threadPoolTaskExecutor.setAwaitTerminationSeconds(30)*/
        return threadPoolTaskExecutor
    }

}