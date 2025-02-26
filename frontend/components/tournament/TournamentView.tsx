import { Container, Flex, Text, Strong, Heading, Box, Card, Avatar, Table} from "@radix-ui/themes";
import type { TournamentWithParticipantsAndMatches } from "../../utils/types";

type TournamentViewProps = {
    tournament: TournamentWithParticipantsAndMatches;
};
export default function TournamentView({
    tournament: {
        tournament_id,
        name,
        start_date,
        start_time,
        location,
        playing_fields,
        time_between_matches,
        participants,
        matches,
    },
}: TournamentViewProps) {
    return (
        <Container>
            <Flex>
                <Heading as="h1">{name}</Heading>
            </Flex>
            <Flex direction="row" align="center" gap="9">
                <Flex direction={"column"} gap="3">
                    <Text><Strong>Start Date: </Strong> {start_date}</Text>
                    <Text><Strong>Start Time: </Strong> {start_time}</Text>
                    <Text><Strong>Location: </Strong> {location}</Text>
                    <Text><Strong>Number of Playing Fields: </Strong>{playing_fields}</Text>
                    <Text><Strong>Time Between Matches: </Strong>{time_between_matches} min</Text>

                </Flex>
                <Flex direction={"column"} gap="3">
                    <Text><Strong>Participants: </Strong></Text>
                    <Flex gap="3">
                        {participants.map((participant) => <Box width={"200px"} height={"200px"} key={participant.participant_id}>
                                                                <Card>
                                                                    <Flex gap="1" align="center" direction={"column"}>
                                                                        <Avatar
                                                                            size="6"
                                                                            fallback={participant.name.split(" ").map((name) => name[0]).join("")}
                                                                        ></Avatar>
                                                                        {participant.name}
                                                                    </Flex>
                                                                </Card>
                                                                
                                                            </Box>)}
                    </Flex>
                </Flex>
            </Flex>
            <Flex>
                <Text><Strong>Matches</Strong></Text>
            </Flex>

            <Table.Root>
                <Table.Header>
                    <Table.Row>
                        <Table.ColumnHeaderCell>Time</Table.ColumnHeaderCell>
                        <Table.ColumnHeaderCell>Match ID</Table.ColumnHeaderCell>
                        <Table.ColumnHeaderCell>Participant 1</Table.ColumnHeaderCell>
                        <Table.ColumnHeaderCell>Participant 2</Table.ColumnHeaderCell>
                        
                        <Table.ColumnHeaderCell>Date</Table.ColumnHeaderCell>
                        <Table.ColumnHeaderCell>Game Location</Table.ColumnHeaderCell>
                    </Table.Row>
                </Table.Header>

                <Table.Body>
                    {matches.map((match) => (
                        
                        <Table.Row key={match.match_id}>
                            <Table.Cell>{match.time}</Table.Cell>
                            <Table.Cell>{match.match_id}</Table.Cell>
                            <Table.Cell>{participants[match.participant1 - 1].name}</Table.Cell>
                            <Table.Cell>{participants[match.participant2 - 1].name}</Table.Cell>
                            <Table.Cell>{match.date.toDateString()}</Table.Cell>
                            <Table.Cell>{match.game_location}</Table.Cell>
                        </Table.Row>
                    ))}
                </Table.Body>
            </Table.Root>
        </Container>
    );
}
//{participants.map((participant) => participant.name).join(", ")}

//{matches.map((match) => match.match_id).join(", ")}