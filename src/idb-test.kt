import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose

val test = """
| {"name": "Apple TV", "udid": "593650CD-2A77-4F70-998E-15B92A21DAA4", "state": "Shutdown", "type": "simulator", "os_version": "tvOS 13.4", "architecture": "x86_64"}
| {"name": "Apple TV 4K", "udid": "D8BF3AFF-EEC2-4393-A3FF-660B0016C705", "state": "Shutdown", "type": "simulator", "os_version": "tvOS 13.4", "architecture": "x86_64"}
| {"name": "Apple TV 4K (at 1080p)", "udid": "73E42B83-967E-4ECE-B386-59290CF6AE78", "state": "Shutdown", "type": "simulator", "os_version": "tvOS 13.4", "architecture": "x86_64"}
| {"name": "Apple Watch Series 4 - 40mm", "udid": "0893A127-CE0A-4618-8C61-D9813CE370E6", "state": "Shutdown", "type": "simulator", "os_version": "watchOS 6.2", "architecture": "i386"}
| {"name": "Apple Watch Series 4 - 44mm", "udid": "C6A7F4EF-4D87-4BF2-B5D1-22ED445849B6", "state": "Shutdown", "type": "simulator", "os_version": "watchOS 6.2", "architecture": "i386"}
| {"name": "Apple Watch Series 5 - 40mm", "udid": "93938A6F-4B41-4B28-8294-6080678B481A", "state": "Shutdown", "type": "simulator", "os_version": "watchOS 6.2", "architecture": "x86_64"}
| {"name": "Apple Watch Series 5 - 44mm", "udid": "5E0E6606-BCF4-4F98-A6A6-F5C7DBCBFE6B", "state": "Shutdown", "type": "simulator", "os_version": "watchOS 6.2", "architecture": "x86_64"}
| {"name": "iPad (7th generation)", "udid": "FB5A1623-8278-4EAC-9E4D-E142DA62D3C3", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPad Air (3rd generation)", "udid": "E8D28CC4-43E6-4313-AF8B-173B2C360657", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPad Pro (11-inch)", "udid": "B29909EC-CB33-4C23-989F-973A53F37AEC", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPad Pro (11-inch) (2nd generation)", "udid": "CA2BACE8-D926-4020-92A5-654169ADCB0B", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPad Pro (12.9-inch) (3rd generation)", "udid": "BB9A23C8-B5F8-48D1-B318-80998EFC8BB5", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPad Pro (12.9-inch) (4th generation)", "udid": "960A3F73-1E99-493B-9F0D-EFFA26D9F319", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPad Pro (9.7-inch)", "udid": "3EC304D7-509C-46FE-8085-49510C8931C2", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPhone", "udid": "d25134066e9755f2c8fc0b31c0480636095ca9ad", "state": "Booted", "type": "device", "os_version": "iOS 13.3.1", "architecture": "arm64"}
| {"name": "iPhone", "udid": "d25134066e9755f2c8fc0b31c0480636095ca9ad", "state": "Booted", "type": "device", "os_version": "iOS 13.3.1", "architecture": "arm64"}
| {"name": "iPhone 11", "udid": "1AA927BC-6441-4648-AD9B-3CE3EA2CE521", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPhone 11 Pro", "udid": "1A3E8273-EA6C-44E9-A446-B3F52B5C6D89", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPhone 11 Pro Max", "udid": "4AEB1A78-93B4-4D34-8D33-953DF9EACC26", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPhone 8", "udid": "7A127D0E-0B7D-4F20-8A64-66C04013D54D", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPhone 8 Plus", "udid": "12917C73-8D2F-48EB-93D2-3A21E5688AAC", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
| {"name": "iPhone SE (2nd generation)", "udid": "31995F7F-F1ED-4E57-8ABC-7270318F6C56", "state": "Shutdown", "type": "simulator", "os_version": "iOS 13.4", "architecture": "x86_64"}
""".split("|")

val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()!!

internal data class DeviceTarget(@Expose val name: String, @Expose val udid: String, @Expose val state: String, @Expose val type: String)
fun main() {
    test.forEach {
        val d = gson.fromJson(it, DeviceTarget::class.java)
        println("'$it' -> ${d?.name}, ${d?.type}, ${d?.udid}")
    }
}

