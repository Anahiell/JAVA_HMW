package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import itstep.learning.services.generators.*;
import itstep.learning.services.hash.HashService;
import itstep.learning.services.hash.Md5HashService;

public class ServiceModule  extends AbstractModule {

    @Override
    protected void configure() {
        //тут надо забиндить сервисы

        bind(GeneratorService.class)
                .annotatedWith(Names.named("file"))
                .to(FileNameGeneratorService.class);
        bind(GeneratorService.class)
                .annotatedWith(Names.named("OTP"))
                .to(OTPGeneratorService.class);
        bind(GeneratorService.class)
                .annotatedWith(Names.named("fasol"))
                .to(FasolGeneratorService.class);
        bind(GeneratorService.class)
                .annotatedWith(Names.named("password"))
                .to(BasicPasswordGeneratorService.class);
        bind(HashService.class).annotatedWith(Names.named("Md5"))
                .to(Md5HashService.class);
    }
}
