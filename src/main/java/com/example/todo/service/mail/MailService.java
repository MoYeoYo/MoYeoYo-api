package com.example.todo.service.mail;

import com.example.todo.domain.repository.UsersSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender javaMailSender;
    private final UsersSubscriptionRepository usersSubscriptionRepository;

    private ThreadPoolTaskExecutor create() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
//        executor.setQueueCapacity(5);
        executor.setThreadNamePrefix("MailSenderThread-");
        executor.initialize();
        return executor;
    }

    //    @Scheduled(cron = "10 * * * * *")
//    @Async
    public void mailSend() throws InterruptedException {
        long beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
        LocalDate localDate = LocalDate.of(2023, 9, 12);
//        List<UsersSubscription> all = usersSubscriptionRepository.customFindAll(localDate);
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        ThreadPoolTaskExecutor executor = create();
        int count = 0;
        for (int i = 0; i < 200; i++) {
            count++;
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            System.out.println("javaMailSender = " + javaMailSender);
            simpleMailMessage.setTo("dbsquddlfz@naver.com");
//            simpleMailMessage.setSubject("안녕하세요 제목입니다." + all.get(i).getId());
            simpleMailMessage.setSubject("안녕하세요 제목입니다." + i);
            simpleMailMessage.setText("내용입니다.");

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                javaMailSender.send(simpleMailMessage);
            }, executor);
            if (count % 50 == 0) {
                Thread.sleep(60000L * 2);
            }
            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long diffTime = (afterTime - beforeTime) / 1000; // 두 개의 실행 시간
        System.out.println("실행 시간(ms): " + diffTime); // 세컨드(초 단위 변환)
    }
//    @Async
//    public void mailSend() {
////        LocalDate localDate = LocalDate.of(2023, 9, 12);
////        List<UsersSubscription> all = usersSubscriptionRepository.customFindAll(localDate);
//        for (int i = 0; i < 30; i++) {
//            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            simpleMailMessage.setTo("dbsquddlfz@naver.com");
////            simpleMailMessage.setSubject("안녕하세요 제목입니다." + all.get(i).getId());
//            simpleMailMessage.setSubject("안녕하세요 제목입니다." + i);
//            simpleMailMessage.setText("내용입니다.");
//            javaMailSender.send(simpleMailMessage);
//        }
//    }
}
