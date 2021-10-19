package dev.katcodes.weirdhappenings.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.katcodes.weirdhappenings.happenings.HappeningBase;
import dev.katcodes.weirdhappenings.happenings.HappeningManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class HappeningsArgument implements ArgumentType<String> {
    private static final Collection<String> EXAMPLES = Arrays.asList("foo", "123");
    private static final DynamicCommandExceptionType ERROR_HAPPENING_NOT_FOUND = new DynamicCommandExceptionType((p_112095_) -> {
        return new TranslatableComponent("happening.notFound", p_112095_);
    });

    public static HappeningsArgument happening() { return new HappeningsArgument(); }

    public static HappeningBase getHappening(CommandContext<CommandSourceStack> p_112092_, String p_112093_) throws CommandSyntaxException {
        String s = p_112092_.getArgument(p_112093_, String.class);
        HappeningBase ret= HappeningManager.getHappeningByName(s);

        if (ret == null) {
            throw ERROR_HAPPENING_NOT_FOUND.create(s);
        } else {
            return ret;
        }
    }
    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        return reader.readUnquotedString();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return context.getSource() instanceof SharedSuggestionProvider ? SharedSuggestionProvider.suggest(HappeningManager.getHappenings(), builder) : Suggestions.empty();
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }
}
