package dev.katcodes.weirdhappenings.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.katcodes.weirdhappenings.happenings.HappeningBase;
import dev.katcodes.weirdhappenings.happenings.HappeningManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.stream.Collectors;

public class HappeningsCommand  {
    public static void init(CommandDispatcher<CommandSourceStack> dispatcher, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("happenings").requires((p_136318_) -> {
            return p_136318_.hasPermission(2);
        }).then(Commands.literal("run").then(Commands.argument("happening",HappeningsArgument.happening()).suggests((context, builder) -> {
            return HappeningsArgument.happening().listSuggestions(context,builder);
        }).executes(context -> {
            return runHappening(context,HappeningsArgument.getHappening(context,"happening"));
        }))).then(Commands.literal("list").executes(HappeningsCommand::listHappenings)));

    }

    private static int listHappenings(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(new TranslatableComponent("The following Happenings are available: "+ HappeningManager.getHappenings().stream().collect(Collectors.joining("\n","","\n"))),false);
        return 1;
    }

    public static int runHappening(CommandContext<CommandSourceStack> context, HappeningBase base){
        if( context.getSource().getEntity() instanceof Player) {
            if(base.runHappening((Player) context.getSource().getEntity())){
                context.getSource().sendSuccess(new TextComponent("Ran Happening Successfully"),false);
            }
        }
        return 1;
    }
}
